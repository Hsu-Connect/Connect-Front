// presentation/mypage/component/common/ConfirmDialog.kt (이름 변경)
package com.hsLink.hslink.presentation.mypage.component.career

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButton
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Composable
fun ConfirmDialog(
    title: String,                    // ← 파라미터로 변경
    message: String? = null,          // ← 파라미터로 변경 (null 가능)
    cancelText: String = "취소하기",   // ← 파라미터로 변경
    confirmText: String = "확인",      // ← 파라미터로 변경
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,  // ← 파라미터 사용
                style = HsLinkTheme.typography.title_20Strong,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = message?.let { // ← null이 아닐 때만 표시
            {
                Text(
                    text = it,
                    style = HsLinkTheme.typography.body_14Normal,
                    color = HsLinkTheme.colors.Grey600,
                    textAlign = TextAlign.Center
                )
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HsLinkSelectButton(
                    label = cancelText,  // ← 파라미터 사용
                    onClick = onDismiss,
                    size = HsLinkButtonSize.Large,
                    isSelected = false,
                    modifier = Modifier.weight(1f)
                )

                HsLinkActionButton(
                    label = confirmText,  // ← 파라미터 사용
                    onClick = onConfirm,
                    size = HsLinkActionButtonSize.Large,
                    modifier = Modifier.weight(1f)
                )
            }
        },
        dismissButton = null,
        modifier = modifier
    )
}