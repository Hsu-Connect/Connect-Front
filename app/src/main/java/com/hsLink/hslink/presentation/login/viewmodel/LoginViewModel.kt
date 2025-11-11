package com.hsLink.hslink.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsLink.hslink.domain.repository.AuthRepository
import com.hsLink.hslink.presentation.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun loginWithKakaoToken(accessToken: String) {
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            authRepository.loginWithSocialToken("kakao", accessToken)
                .onSuccess { response ->
                    println("서버 로그인 성공: $response")
                    _loginState.value = LoginState.Success(
                        isNewUser = response.isNewUser,
                        needsOnboarding = response.needsOnboarding
                    )
                }
                .onFailure { error ->
                    println("서버 로그인 실패: ${error.message}")
                    println("에러 상세: ${error.printStackTrace()}")
                    _loginState.value = LoginState.Error(
                        error.message ?: "로그인에 실패했습니다"
                    )
                }
        }
    }

    fun resetLoginState() {
        _loginState.value = LoginState.Idle
    }
}