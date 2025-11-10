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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.login.component.KakaoLoginButton

@Preview(showBackground = true)
@Composable
fun KaKaoLoginScreenPreview() {
    KaKaoLoginScreen()
}

@Composable
fun KaKaoLoginScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        HsLinkTheme.colors.SkyBlue200,
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
                verticalArrangement = Arrangement.Center) {
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
            Spacer(modifier.padding(115.dp))
            KakaoLoginButton(
                onClick = { /* TODO: 카카오 로그인 로직 */ }
            )
        }
    }
}