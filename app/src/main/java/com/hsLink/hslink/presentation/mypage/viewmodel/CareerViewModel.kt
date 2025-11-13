package com.hsLink.hslink.presentation.mypage.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsLink.hslink.data.dto.request.onboarding.CareerUpdateRequestDto
import com.hsLink.hslink.data.dto.response.onboarding.CareerDto
import com.hsLink.hslink.domain.repository.CareerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CareerViewModel @Inject constructor(
    private val careerRepository: CareerRepository
) : ViewModel() {

    private val _careers = MutableStateFlow<List<CareerDto>>(emptyList())
    val careers: StateFlow<List<CareerDto>> = _careers.asStateFlow()

    private val _selectedCareer = MutableStateFlow<CareerDto?>(null)
    val selectedCareer: StateFlow<CareerDto?> = _selectedCareer.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadMyCareers() {
        viewModelScope.launch {
            _isLoading.value = true
            Log.d("CareerViewModel", "커리어 목록 조회 시작")

            careerRepository.getMyCareers().fold(
                onSuccess = { careerList ->
                    _careers.value = careerList
                    _error.value = null
                    Log.d("CareerViewModel", "커리어 조회 성공: ${careerList.size}개")
                },
                onFailure = { exception ->
                    _error.value = exception.message
                    Log.e("CareerViewModel", "커리어 조회 실패", exception)
                }
            )
            _isLoading.value = false
        }
    }

    fun updateCareer(careerId: Long, request: CareerUpdateRequestDto) {
        viewModelScope.launch {
            _isLoading.value = true
            Log.d("CareerViewModel", "커리어 수정 시작: $careerId")

            careerRepository.updateCareer(careerId, request).fold(
                onSuccess = {
                    Log.d("CareerViewModel", "커리어 수정 성공")
                    // 수정 후 목록 다시 조회
                    loadMyCareers()
                },
                onFailure = { exception ->
                    _error.value = exception.message
                    Log.e("CareerViewModel", "커리어 수정 실패", exception)
                }
            )
            _isLoading.value = false
        }
    }

    fun loadCareer(careerId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            Log.d("CareerViewModel", "커리어 단건 조회 시작: $careerId")

            careerRepository.getCareer(careerId).fold(
                onSuccess = { career ->
                    _selectedCareer.value = career
                    _error.value = null
                    Log.d("CareerViewModel", "커리어 조회 성공: ${career.companyName}")
                },
                onFailure = { exception ->
                    _error.value = exception.message
                    Log.e("CareerViewModel", "커리어 조회 실패", exception)
                }
            )
            _isLoading.value = false
        }
    }
}