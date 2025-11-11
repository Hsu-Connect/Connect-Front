package com.hsLink.hslink.presentation.search.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsLink.hslink.domain.repository.search.SearchRepository
import com.hsLink.hslink.presentation.search.state.ProfileIntent
import com.hsLink.hslink.presentation.search.state.ProfileSideEffect
import com.hsLink.hslink.presentation.search.state.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<ProfileSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun handleIntent(intent: ProfileIntent) {
        Log.d("ProfileViewModel", "handleIntent: $intent")
        when (intent) {
            is ProfileIntent.LoadProfile -> loadProfile(intent.userId)
            is ProfileIntent.ClearError -> clearError()
            is ProfileIntent.NavigateBack -> navigateBack()
        }
    }

    private fun loadProfile(userId: Long) {
        Log.d("ProfileViewModel", "loadProfile 시작: userId=$userId")
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )
            Log.d("ProfileViewModel", "Loading 상태 설정 완료")

            searchRepository.getUserProfile(userId)
                .onSuccess { userProfile ->
                    Log.d("ProfileViewModel", "프로필 로드 성공: ${userProfile.name}")
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        userProfile = userProfile
                    )
                }
                .onFailure { exception ->
                    Log.e("ProfileViewModel", "프로필 로드 실패: ${exception.message}", exception)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message
                    )
                    postSideEffect(ProfileSideEffect.ShowError(exception.message ?: "Unknown error"))
                }
        }
    }

    private fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    private fun navigateBack() {
        viewModelScope.launch {
            postSideEffect(ProfileSideEffect.NavigateBack)
        }
    }

    private suspend fun postSideEffect(sideEffect: ProfileSideEffect) {
        _sideEffect.emit(sideEffect)
    }
}