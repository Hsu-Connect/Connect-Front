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
fun MentorshipScreen(
    wantsMentorship: Boolean?,
    progress: Float,
    paddingValues: PaddingValues,
    onMentorshipSelect: (Boolean) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    OnboardingScreen(
        title = buildAnnotatedString {
            append("멘토로도 참여해보시겠어요? ")
            withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                append("*")
            }
        },
        subtitle = "모든 사용자는 멘티로 사작하며, 원할경우 멘토로드 활동에서 초대되니 구독 제공을 받을 수 있어요.",
        progress = progress,
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = wantsMentorship != null,
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HsLinkSelectButton(
                label = "멘토로도 참여할래요",
                onClick = { onMentorshipSelect(true) },
                size = HsLinkButtonSize.Large,
                modifier = Modifier.fillMaxWidth(),
                isEnabled = true,
                isSelected = wantsMentorship == true
            )

            HsLinkSelectButton(
                label = "멘티로만 참여할래요",
                onClick = { onMentorshipSelect(false) },
                size = HsLinkButtonSize.Large,
                modifier = Modifier.fillMaxWidth(),
                isEnabled = true,
                isSelected = wantsMentorship == false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MentorshipScreenPreview() {
    HsLinkTheme {
        MentorshipScreen(
            wantsMentorship = true,
            progress = 0.8f,
            paddingValues = PaddingValues(),
            onMentorshipSelect = {},
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}
