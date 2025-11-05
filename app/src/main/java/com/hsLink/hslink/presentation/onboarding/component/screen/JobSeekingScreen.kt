package com.hsLink.hslink.presentation.onboarding.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen

@Composable
fun JobSeekingScreen(
    isJobSeeking: Boolean?,
    progress: Float,
    paddingValues: PaddingValues,
    onJobSeekingSelect: (Boolean) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    OnboardingScreen(
        title = buildAnnotatedString {
            append("현재 구직 중이신가요? ")
            withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                append("*")
            }
        },
        subtitle = "구직 중이시면 멘토의 조언을 받을 수 있어요.",
        progress = progress,
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = isJobSeeking != null,
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HsLinkSelectButton(
                label = "구직 중이에요",
                onClick = { onJobSeekingSelect(true) },
                size = HsLinkButtonSize.Large,
                modifier = Modifier.fillMaxWidth(),
                isEnabled = true,
                isSelected = isJobSeeking == true
            )

            HsLinkSelectButton(
                label = "구직하지 않아요",
                onClick = { onJobSeekingSelect(false) },
                size = HsLinkButtonSize.Large,
                modifier = Modifier.fillMaxWidth(),
                isEnabled = true,
                isSelected = isJobSeeking == false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun JobSeekingScreenPreview() {
    HsLinkTheme {
        JobSeekingScreen(
            isJobSeeking = true,
            progress = 0.6f,
            paddingValues = PaddingValues(),
            onJobSeekingSelect = {},
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}
