package com.hsLink.hslink.presentation.mypage.component.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Composable
fun ErrorTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isFocused: Boolean,
    onFocusChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    isRequired: Boolean = true,
    isError: Boolean = false,
    errorMessage: String = "",
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 라벨 영역
        Row {
            Text(
                text = label,
                color = HsLinkTheme.colors.Grey700,
                style = HsLinkTheme.typography.title_14Strong
            )
            if (isRequired) {
                Text(
                    text = " *",
                    color = Color.Red,
                    style = HsLinkTheme.typography.title_14Strong
                )
            }
        }

        // TextField
        HsLinkTextField(
            value = value,
            placeholder = placeholder,
            onValueChanged = onValueChange,
            borderColor = when {
                isError -> Color.Red
                isFocused -> HsLinkTheme.colors.DeepBlue500
                else -> HsLinkTheme.colors.Grey200
            },
            backgroundColor = HsLinkTheme.colors.Common,
            onFocusChanged = onFocusChanged,
            modifier = Modifier.fillMaxWidth()
        )

        // 에러 메시지
        if (isError && errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = HsLinkTheme.typography.caption_12Normal
            )
        }
    }
}

@Preview(showBackground = true, name = "정상 상태")
@Composable
private fun ErrorTextFieldNormalPreview() {
    HsLinkTheme {
        ErrorTextField(
            label = "학번",
            value = "2131114",
            onValueChange = {},
            placeholder = "학번을 입력하세요",
            isFocused = false,
            onFocusChanged = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, name = "에러 상태")
@Composable
private fun ErrorTextFieldErrorPreview() {
    HsLinkTheme {
        ErrorTextField(
            label = "학번",
            value = "213",
            onValueChange = {},
            placeholder = "학번을 입력하세요",
            isFocused = false,
            onFocusChanged = {},
            isError = true,
            errorMessage = "7자 이상의 숫자를 입력해주세요",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, name = "포커스 상태")
@Composable
private fun ErrorTextFieldFocusedPreview() {
    HsLinkTheme {
        ErrorTextField(
            label = "학번",
            value = "2131114",
            onValueChange = {},
            placeholder = "학번을 입력하세요",
            isFocused = true,
            onFocusChanged = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}