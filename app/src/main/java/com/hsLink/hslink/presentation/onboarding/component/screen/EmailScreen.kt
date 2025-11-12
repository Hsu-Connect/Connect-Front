package com.hsLink.hslink.presentation.onboarding.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen

@Composable
fun EmailScreen(
    email: String,
    progress: Float,
    paddingValues: PaddingValues,
    onEmailChange: (String) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    OnboardingScreen(
        title = buildAnnotatedString {
            append("이메일을 입력해주세요 ")
            withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                append("*")
            }
        },
        subtitle = "본인 확인 및 계정 분실 시 사용됩니다.",
        progress = progress,
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = email.isNotEmpty(),
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("이메일 ")
                    withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                        append("*")
                    }
                },
                color = HsLinkTheme.colors.Grey700,
                style = HsLinkTheme.typography.title_14Strong
            )
            HsLinkTextField(
                value = email,
                placeholder = "이메일을 입력해주세요",
                onValueChanged = onEmailChange,
                borderColor = if (isFocused) HsLinkTheme.colors.DeepBlue500
                else HsLinkTheme.colors.Grey300,
                backgroundColor = HsLinkTheme.colors.Common,
                onFocusChanged = { isFocused = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                imeAction = ImeAction.Done,
                onDoneAction = { if (email.isNotEmpty()) onNextClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmailScreenPreview() {
    HsLinkTheme {
        EmailScreen(
            email = "hsu@connect.com",
            progress = 0.9f,
            paddingValues = PaddingValues(),
            onEmailChange = {},
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}