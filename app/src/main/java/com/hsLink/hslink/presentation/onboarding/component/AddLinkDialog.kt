package com.hsLink.hslink.presentation.onboarding.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.onboarding.model.ExternalLink
import com.hsLink.hslink.presentation.onboarding.model.LinkType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLinkDialog(
    onDismiss: () -> Unit,
    onConfirm: (ExternalLink) -> Unit,
) {
    var selectedLinkType by remember { mutableStateOf<LinkType?>(null) }
    var url by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var urlFocused by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(HsLinkTheme.colors.Common, RoundedCornerShape(12.dp))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "링크 등록",
                style = HsLinkTheme.typography.title_20Strong,
                color = HsLinkTheme.colors.Grey700
            )

            Text(
                text = "나눔에 관련을 할 때 기본 정보로 활용됩니다.",
                style = HsLinkTheme.typography.body_14Normal,
                color = HsLinkTheme.colors.Grey600
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "링크 유형 구분",
                style = HsLinkTheme.typography.body_14Normal,
                color = HsLinkTheme.colors.Grey500
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                HsLinkTextField(
                    value = selectedLinkType?.label ?: "",
                    placeholder = "링크 유형",
                    onValueChanged = {},
                    borderColor = HsLinkTheme.colors.Grey300,
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    leadingIconRes = null
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    LinkType.entries.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type.label) },
                            onClick = {
                                selectedLinkType = type
                                expanded = false
                            }
                        )
                    }
                }
            }

            Text(
                text = "URL",
                style = HsLinkTheme.typography.body_14Normal,
                color = HsLinkTheme.colors.Grey500
            )

            HsLinkTextField(
                value = url,
                placeholder = "URL 주소를 입력하세요",
                onValueChanged = { url = it },
                borderColor = if (urlFocused) HsLinkTheme.colors.SkyBlue500
                else HsLinkTheme.colors.Grey300,
                backgroundColor = HsLinkTheme.colors.Common,
                onFocusChanged = { urlFocused = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HsLinkSelectButton(
                    label = "취소",
                    onClick = onDismiss,
                    size = HsLinkButtonSize.Medium,
                    modifier = Modifier.weight(1f),
                    isEnabled = true,
                    isSelected = false
                )

                HsLinkSelectButton(
                    label = "저장하기",
                    onClick = {
                        selectedLinkType?.let { type ->
                            if (url.isNotEmpty()) {
                                onConfirm(ExternalLink(type, url))
                            }
                        }
                    },
                    size = HsLinkButtonSize.Medium,
                    modifier = Modifier.weight(1f),
                    isEnabled = selectedLinkType != null && url.isNotEmpty(),
                    isSelected = selectedLinkType != null && url.isNotEmpty()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewAddLinkDialog(){
    HsLinkTheme {
        AddLinkDialog(
            onDismiss = {},
            onConfirm = {}
        )
    }
}