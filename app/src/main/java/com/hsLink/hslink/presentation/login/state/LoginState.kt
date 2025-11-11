package com.hsLink.hslink.presentation.login.state

// 로그인의 여러 상태를 정의하는 클래스
sealed class LoginState {
    object Idle : LoginState()     // 대기중 (아무것도 안 한 상태)
    object Loading : LoginState()  // 로딩중 (로그인 진행중)
    data class Success(             // 성공 (로그인 완료)
        val isNewUser: Boolean,        // 새로운 사용자인지
        val needsOnboarding: Boolean   // 온보딩이 필요한지
    ) : LoginState()
    data class Error(               // 실패 (에러 발생)
        val message: String            // 에러 메시지
    ) : LoginState()
}