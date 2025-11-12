package com.hsLink.hslink.presentation.onboarding.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen
import com.hsLink.hslink.presentation.onboarding.model.LinkType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LinkAddScreen(
    type: LinkType?,
    url: String,
    progress: Float,
    paddingValues: PaddingValues,
    onTypeSelect: (LinkType) -> Unit,
    onUrlChange: (String) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var urlFocused by remember { mutableStateOf(false) }

    val isFormValid = type != null && url.isNotBlank() &&
            (url.startsWith("http://") || url.startsWith("https://"))

    OnboardingScreen(
        title = buildAnnotatedString {
            append("링크 등록")
        },
        progress = progress,
        paddingValues = paddingValues,
        subtitle = "나중에 멘토링 할 때 기본 정보로 활용됩니다.",
        showPreviousButton = true,
        nextButtonEnabled = isFormValid,
        nextButtonLabel = "저장하기",
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = buildAnnotatedString {
                        append("링크 유형 구분 ")
                        withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                            append("*")
                        }
                    },
                    style = HsLinkTheme.typography.title_14Strong,
                    color = HsLinkTheme.colors.Grey700
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    LinkType.entries.forEach { linkType ->
                        HsLinkSelectButton(
                            label = linkType.label,
                            onClick = { onTypeSelect(linkType) },
                            size = HsLinkButtonSize.Medium,
                            isSelected = type == linkType
                        )
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = buildAnnotatedString {
                        append("URL ")
                        withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                            append("*")
                        }
                    },
                    style = HsLinkTheme.typography.title_14Strong,
                    color = HsLinkTheme.colors.Grey700
                )
                HsLinkTextField(
                    value = url,
                    placeholder = "https://example.com",
                    onValueChanged = onUrlChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { urlFocused = it.isFocused },
                    borderColor = if (urlFocused) HsLinkTheme.colors.SkyBlue500 else HsLinkTheme.colors.Grey300,
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { }
                )

                if (url.isNotBlank() && !url.startsWith("http://") && !url.startsWith("https://")) {
                    Text(
                        text = "URL은 http:// 또는 https://로 시작해야 합니다",
                        style = HsLinkTheme.typography.body_14Normal,
                        color = HsLinkTheme.colors.Red500
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LinkAddScreenPreview() {
    HsLinkTheme {
        LinkAddScreen(
            type = LinkType.GITHUB,
            url = "https://github.com/hsu-link",
            progress = 0.9f,
            paddingValues = PaddingValues(0.dp),
            onTypeSelect = {},
            onUrlChange = {},
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}