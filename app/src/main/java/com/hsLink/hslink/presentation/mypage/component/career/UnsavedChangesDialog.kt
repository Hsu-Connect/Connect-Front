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
fun UnsavedChangesDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "입력한 내용이 저장되지 않아요",
                style = HsLinkTheme.typography.title_20Strong,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Text(
                text = "지금까지 입력한 내용은 저장되지 않습니다.\n그래도 나가시겠어요?",
                style = HsLinkTheme.typography.body_14Normal,
                color = HsLinkTheme.colors.Grey600,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HsLinkSelectButton(
                    label = "취소하기",
                    onClick = onDismiss,
                    size = HsLinkButtonSize.Large,
                    isSelected = false,
                    modifier = Modifier.weight(1f)
                )

                HsLinkActionButton(
                    label = "나가기",
                    onClick = onConfirm,
                    size = HsLinkActionButtonSize.Large,
                    modifier = Modifier.weight(1f)  // ← Row 내부에서는 가능
                )
            }
        },
        dismissButton = null,  // ← confirmButton에서 모든 버튼 처리
        modifier = modifier
    )
}