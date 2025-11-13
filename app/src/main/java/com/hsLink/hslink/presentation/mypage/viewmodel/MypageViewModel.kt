// presentation/mypage/viewmodel/MypageViewModel.kt
package com.hsLink.hslink.presentation.mypage.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsLink.hslink.data.dto.request.mypage.UpdateProfileRequestDto
import com.hsLink.hslink.data.dto.response.mypage.MyPageUserProfileDto
import com.hsLink.hslink.data.dto.response.mypage.MyPageUserSummaryDto
import com.hsLink.hslink.data.dto.response.mypage.UserProfileDto
import com.hsLink.hslink.domain.repository.mypage.MypageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val mypageRepository: MypageRepository
) : ViewModel() {

    private val _userProfile = MutableStateFlow<MyPageUserProfileDto?>(null)
    val userProfile: StateFlow<MyPageUserProfileDto?> = _userProfile.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        getUserSummary() // ← getUserProfile() 대신 변경
    }

    fun loadUserProfile() {
        getUserProfile()
    }

    // ← 새로 추가: public loadUserSummary 함수
    fun loadUserSummary() {
        getUserSummary()
    }
    private fun getUserProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            Log.d("MypageViewModel", "API 호출 시작") // <- 로그 추가
            mypageRepository.getUserProfile()
                .onSuccess { profile ->
                    Log.d("MypageViewModel", "API 성공: ${profile.name}") // <- 로그 추가
                    _userProfile.value = profile
                    _error.value = null
                }
                .onFailure { exception ->
                    Log.e("MypageViewModel", "API 실패: ${exception.message}") // <- 로그 추가
                    _error.value = exception.message
                }
            _isLoading.value = false
        }
    }

    fun updateProfile(
        studentNumber: String? = null,
        name: String? = null,
        major: String? = null,
        mentor: Boolean? = null,
        jobSeeking: Boolean? = null
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            val request = UpdateProfileRequestDto(studentNumber, name, major, mentor, jobSeeking)
            mypageRepository.updateProfile(request)
                .onSuccess {
                    Log.d("MypageViewModel", "프로필 수정 성공")
                    // 수정 후 다시 조회
                    getUserProfile() // ← 이건 그대로 유지 (전체 정보 필요)
                    getUserSummary()
                }
                .onFailure { exception ->
                    Log.e("MypageViewModel", "프로필 수정 실패: ${exception.message}")
                    _error.value = exception.message
                }
            _isLoading.value = false
        }
    }
    private val _userSummary = MutableStateFlow<MyPageUserSummaryDto?>(null)
    val userSummary: StateFlow<MyPageUserSummaryDto?> = _userSummary.asStateFlow()

    private fun getUserSummary() {
        viewModelScope.launch {
            _isLoading.value = true
            Log.d(TAG, "Summary API 호출 시작")

            mypageRepository.getUserSummary().fold(
                onSuccess = { summary ->
                    _userSummary.value = summary
                    Log.d(TAG, "Summary API 성공: ${summary.name}")
                },
                onFailure = { exception ->
                    Log.e(TAG, "Summary API 실패", exception)
                }
            ).also {
                _isLoading.value = false
            }
        }
    }
}