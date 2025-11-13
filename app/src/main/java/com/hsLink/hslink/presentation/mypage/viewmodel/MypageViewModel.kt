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
import com.hsLink.hslink.domain.repository.AuthRepository
import com.hsLink.hslink.domain.repository.mypage.MypageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val mypageRepository: MypageRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _userProfile = MutableStateFlow<MyPageUserProfileDto?>(null)
    val userProfile: StateFlow<MyPageUserProfileDto?> = _userProfile.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // ← 로그아웃 성공 이벤트 추가
    private val _logoutSuccess = MutableStateFlow(false)
    val logoutSuccess: StateFlow<Boolean> = _logoutSuccess.asStateFlow()

    // ← 탈퇴 성공 이벤트 추가
    private val _withdrawSuccess = MutableStateFlow(false)
    val withdrawSuccess: StateFlow<Boolean> = _withdrawSuccess.asStateFlow()


    // ← 로그아웃/탈퇴 상태 추가
    private val _isAuthLoading = MutableStateFlow(false)
    val isAuthLoading: StateFlow<Boolean> = _isAuthLoading.asStateFlow()

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
    // ← 새로 추가: 로그아웃 기능
    fun logout() {
        viewModelScope.launch {
            _isAuthLoading.value = true
            Log.d("MypageViewModel", "로그아웃 시작")

            authRepository.logout().fold(
                onSuccess = {
                    Log.d("MypageViewModel", "로그아웃 성공")
                    _error.value = null
                    _logoutSuccess.value = true // ← 성공 이벤트 발생
                },
                onFailure = { exception ->
                    Log.e("MypageViewModel", "로그아웃 실패: ${exception.message}")
                    _error.value = exception.message
                }
            )
            _isAuthLoading.value = false
        }
    }

    fun withdraw() {
        viewModelScope.launch {
            _isAuthLoading.value = true
            Log.d("MypageViewModel", "계정 탈퇴 시작")

            authRepository.withdraw().fold(
                onSuccess = { withdrawResponse ->
                    Log.d("MypageViewModel", "계정 탈퇴 성공: userId=${withdrawResponse.userId}")
                    _error.value = null
                    _withdrawSuccess.value = true // ← 성공 이벤트 발생
                },
                onFailure = { exception ->
                    Log.e("MypageViewModel", "계정 탈퇴 실패: ${exception.message}")
                    _error.value = exception.message
                }
            )
            _isAuthLoading.value = false
        }
    }
}