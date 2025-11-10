package com.hsLink.hslink.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable

@Composable
fun HsLinkDialog(
    title: String,
    message: String,
    confirmText: String,
    dismissText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = HsLinkTheme.colors.Common,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = title,
                color = HsLinkTheme.colors.Grey700,
                style = HsLinkTheme.typography.title_20Strong
            )

            Text(
                text = message,
                color = HsLinkTheme.colors.Grey500,
                style = HsLinkTheme.typography.body_14Normal
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = HsLinkTheme.colors.Grey100,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .noRippleClickable(onClick = onDismiss)
                        .padding(vertical = 12.dp, horizontal = 40.dp)
                ) {
                    Text(
                        text = dismissText,
                        color = HsLinkTheme.colors.Grey500,
                        style = HsLinkTheme.typography.btm_S,
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .background(
                            color = HsLinkTheme.colors.DeepBlue500,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .noRippleClickable(onClick = onConfirm)
                        .padding(vertical = 12.dp, horizontal = 40.dp)
                ) {
                    Text(
                        text = confirmText,
                        color = HsLinkTheme.colors.Common,
                        style = HsLinkTheme.typography.btm_S,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HsLinkDialogPreview() {
    HsLinkTheme {
        HsLinkDialog(
            title = "글쓰기를 종료하시겠습니까?",
            message = "작성 중인 내용이 저장되지 않습니다.",
            confirmText = "나가기",
            dismissText = "취소하기",
            onConfirm = {},
            onDismiss = {}
        )
    }
}
