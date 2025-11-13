// presentation/mypage/component/common/ConfirmDialog.kt (이름 변경)
package com.hsLink.hslink.presentation.mypage.component.career

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButton
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme


@Preview(showBackground = true)
@Composable
private fun ConfirmDialogLogoutPreview() {
    HsLinkTheme {
        ConfirmDialog(
            title = "로그아웃을\n하시겠습니까?",
            message = null,
            cancelText = "취소",
            confirmText = "확인",
            onDismiss = {},
            onConfirm = {}
        )
    }
}

@Composable
fun ConfirmDialog(
    modifier: Modifier = Modifier,
    title: String,
    message: String? = null,
    cancelText: String = "취소",
    confirmText: String = "확인",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color.White, // ← 완전 하얀색 배경
        title = {
            Text(
                text = title,
                style = HsLinkTheme.typography.title_20Strong,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = message?.let {
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp), // ← 전체 여백
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = it,
                        style = HsLinkTheme.typography.body_14Normal,
                        color = HsLinkTheme.colors.Grey600,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp, // ← 줄 간격 고정값
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // ← 취소하기 (회색 배경)
                HsLinkActionButton(
                    label = cancelText, // "취소하기"
                    onClick = onDismiss,
                    size = HsLinkActionButtonSize.Small, // ← 회색 배경
                    modifier = Modifier.weight(1f)
                )

                // ← 확인 버튼 (파란색 배경)
                HsLinkActionButton(
                    label = confirmText, // "로그아웃" 또는 "삭제하기"
                    onClick = onConfirm,
                    size = HsLinkActionButtonSize.Large, // ← 파란색 배경
                    modifier = Modifier.weight(1f)
                )
            }
        },
        dismissButton = null,
        modifier = modifier
    )
}