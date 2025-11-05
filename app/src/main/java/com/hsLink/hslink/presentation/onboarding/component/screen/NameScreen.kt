package com.hsLink.hslink.presentation.onboarding.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen

@Composable
fun NameScreen(
    name: String,
    progress: Float,
    paddingValues: PaddingValues,
    onNameChange: (String) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    OnboardingScreen(
        title = buildAnnotatedString {
            append("이름을 입력해주세요 ")
            withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                append("*")
            }
        },
        progress = progress,
        subtitle = "실명을 사용해야 합니다.",
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = name.isNotEmpty(),
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("이름 ")
                    withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                        append("*")
                    }
                },
                color = HsLinkTheme.colors.Grey700,
                style = HsLinkTheme.typography.title_14Strong
            )
            HsLinkTextField(
                value = name,
                placeholder = "이름을 입력해주세요",
                onValueChanged = onNameChange,
                borderColor = if (isFocused) HsLinkTheme.colors.DeepBlue500
                else HsLinkTheme.colors.Grey300,
                backgroundColor = HsLinkTheme.colors.Common,
                onFocusChanged = { isFocused = it },
                modifier = Modifier.fillMaxWidth(),
                imeAction = ImeAction.Done,
                onDoneAction = { if (name.isNotEmpty()) onNextClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NameScreenPreview() {
    HsLinkTheme {
        NameScreen(
            name = "김민준",
            progress = 0.25f,
            paddingValues = PaddingValues(),
            onNameChange = {},
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}
