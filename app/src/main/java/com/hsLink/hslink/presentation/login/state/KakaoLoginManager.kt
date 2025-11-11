package com.hsLink.hslink.presentation.login.state

import android.content.Context
import com.kakao.sdk.user.UserApiClient

// 카카오 로그인을 처리하는 도우미 클래스
object KakaoLoginManager {

    // 카카오 로그인을 시작하는 함수
    fun startKakaoLogin(
        context: Context,  // 현재 화면 정보
        onResult: (String?, Throwable?) -> Unit  // 결과를 받을 함수
    ) {
        // 1. 카카오톡이 설치되어 있는지 확인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            // 카카오톡으로 로그인 시도
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    // 카카오톡 로그인 실패 -> 카카오계정으로 시도
                    loginWithKakaoAccount(context, onResult)
                } else {
                    // 카카오톡 로그인 성공
                    onResult(token?.accessToken, null)
                }
            }
        } else {
            // 카카오톡이 없으면 바로 카카오계정으로 로그인
            loginWithKakaoAccount(context, onResult)
        }
    }

    // 카카오계정으로 로그인하는 함수
    private fun loginWithKakaoAccount(
        context: Context,
        onResult: (String?, Throwable?) -> Unit
    ) {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            onResult(token?.accessToken, error)
        }
    }
}