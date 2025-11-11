package com.hsLink.hslink.presentation.login.component

import android.R.attr.enabled
import androidx.compose.foundation.Image
import androidx.compose.foundation.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KakaoLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFEE500), // 카카오 노란색
            contentColor = Color.Black          // 텍스트 및 아이콘 색상
        ),
        shape = RoundedCornerShape(6.dp),      // 모서리 둥글기
        modifier = Modifier
            .fillMaxWidth(0.85f)               // 가로 폭 (조정 가능)
            .height(54.dp)                     // 높이 (Figma 기준)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 말풍선 아이콘 (SVG 또는 PNG 리소스)
            Image(
                painter = painterResource(id = com.hsLink.hslink.R.drawable.ic_kakao_icon),
                contentDescription = "카카오 로고",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = "카카오 로그인",
                color = Color(0xFF191919),     // 카카오 가이드 기준 검정 85%
                fontSize = 16.sp
            )
        }
    }
}
