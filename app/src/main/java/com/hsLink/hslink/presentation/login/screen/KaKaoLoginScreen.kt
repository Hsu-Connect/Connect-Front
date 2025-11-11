package com.hsLink.hslink.presentation.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.login.component.KakaoLoginButton
import com.hsLink.hslink.presentation.login.state.KakaoLoginManager
import com.hsLink.hslink.presentation.login.state.LoginState
import com.hsLink.hslink.presentation.login.viewmodel.LoginViewModel

@Preview(showBackground = true)
@Composable
fun KaKaoLoginScreenPreview() {
    KaKaoLoginScreen()
}


@Composable
fun KaKaoLoginScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToOnboarding: () -> Unit = {}
) {
    val context = LocalContext.current
    val loginState by viewModel.loginState.collectAsState()

    // 로그인 성공 시 네비게이션 처리
    LaunchedEffect(loginState) {
        // 지역 변수로 만들어서 스마트 캐스팅 가능하게 만들기
        val currentState = loginState
        when (currentState) {
            is LoginState.Success -> {
                if (currentState.needsOnboarding) {
                    onNavigateToOnboarding()
                } else {
                    onNavigateToHome()
                }
            }

            else -> {}
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        HsLinkTheme.colors.SkyBlue400,
                        Color.White
                    ),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = com.hsLink.hslink.R.drawable.img_login_logo),
                    contentDescription = "앱 로고",
                    modifier = Modifier.size(213.dp)
                )
                Text(
                    text = "한성인들이 연결되는 곳",
                    style = HsLinkTheme.typography.title_24Strong,
                    color = HsLinkTheme.colors.DeepBlue600
                )
            }

            Spacer(modifier = Modifier.padding(115.dp))

            KakaoLoginButton(
                onClick = {
                    KakaoLoginManager.startKakaoLogin(context) { accessToken, error ->
                        if (error != null) {
                            // TODO: 에러 처리 개선
                            println("카카오 로그인 실패: ${error.message}")
                        } else if (accessToken != null) {
                            // 서버에 토큰 전송
                            viewModel.loginWithKakaoToken(accessToken)
                        }
                    }
                },
                enabled = loginState !is LoginState.Loading
            )

            // 로그인 상태에 따른 UI - 여기도 지역 변수 사용
            val currentLoginState = loginState
            when (currentLoginState) {
                is LoginState.Loading -> {
                    Spacer(modifier = Modifier.padding(16.dp))
                    CircularProgressIndicator(
                        color = HsLinkTheme.colors.DeepBlue600
                    )
                    Text(
                        text = "로그인 중...",
                        style = HsLinkTheme.typography.body_14Normal,
                        color = HsLinkTheme.colors.DeepBlue600,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                is LoginState.Error -> {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = currentLoginState.message,
                        color = Color.Red,
                        style = HsLinkTheme.typography.body_14Normal
                    )
                }

                is LoginState.Success -> {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = "로그인 성공!",
                        color = Color.Green,
                        style = HsLinkTheme.typography.body_14Normal
                    )
                }

                LoginState.Idle -> {
                    // 아무것도 표시하지 않음
                }
            }
        }
    }
}