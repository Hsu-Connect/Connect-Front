package com.hsLink.hslink.presentation.onboarding.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen
import com.hsLink.hslink.presentation.onboarding.model.ExternalLink
import com.hsLink.hslink.presentation.onboarding.model.LinkType
import com.hsLink.hslink.presentation.onboarding.screen.AddLinkDialog

@Composable
fun LinksScreen(
    links: List<ExternalLink>,
    progress: Float,
    paddingValues: PaddingValues,
    onAddLink: (ExternalLink) -> Unit,
    onRemoveLink: (ExternalLink) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    var showLinkDialog by remember { mutableStateOf(false) }

    OnboardingScreen(
        title = "자신을 소개할 수 있는\n외부 링크를 추가해주세요",
        progress = progress,
        subtitle = "자신을 소개할 수 있는 링크가 없으면 넘겨도 괜찮아요",
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = true,
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            if (links.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            color = HsLinkTheme.colors.Common,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = HsLinkTheme.colors.Grey100,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "등록된 링크가 없어요",
                            style = HsLinkTheme.typography.title_16Strong,
                            color = HsLinkTheme.colors.Grey700
                        )
                        Text(
                            text = "자신을 소개할 수 있는 링크를 추가해보세요",
                            style = HsLinkTheme.typography.body_14Normal,
                            color = HsLinkTheme.colors.Grey500
                        )
                    }
                }

            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    links.forEach { link ->
                        LinkItem(
                            link = link,
                            onClick = { onRemoveLink(link) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            HsLinkSelectButton(
                label = "+ 추가하기",
                onClick = { showLinkDialog = true },
                size = HsLinkButtonSize.Large,
                modifier = Modifier.fillMaxWidth(),
                isEnabled = true,
                isSelected = false
            )
        }
    }

    if (showLinkDialog) {
        AddLinkDialog(
            onDismiss = { showLinkDialog = false },
            onConfirm = { link ->
                onAddLink(link)
                showLinkDialog = false
            }
        )
    }
}


@Composable
private fun LinkItem(
    link: ExternalLink,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(HsLinkTheme.colors.Common)
            .noRippleClickable(onClick = onClick)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = link.type.label,
                style = HsLinkTheme.typography.body_16Normal,
                color = HsLinkTheme.colors.Grey600
            )

            Text(
                text = link.url.extractDomain(),
                style = HsLinkTheme.typography.body_14Normal,
                color = HsLinkTheme.colors.Grey400
            )
        }

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_home_post_arrow),
            contentDescription = "상세보기",
            tint = HsLinkTheme.colors.Grey300
        )
    }
}

private fun String.extractDomain(): String {
    return try {
        val withoutProtocol = this.removePrefix("https://")
            .removePrefix("http://")
            .removePrefix("www.")

        val domain = withoutProtocol.split("/")[0]

        when {
            domain.contains("instagram.com") -> "인스타그램"
            domain.contains("github.com") -> "깃허브"
            domain.contains("linkedin.com") -> "링크드인"
            domain.contains("notion.so") || domain.contains("notion.site") -> "노션"
            else -> domain.take(30)
        }
    } catch (e: Exception) {
        this.take(30)
    }
}

@Preview(showBackground = true)
@Composable
private fun LinksScreenWithDataPreview() {
    HsLinkTheme {
        LinksScreen(
            links = listOf(
                ExternalLink(LinkType.GITHUB, "https://instagram.com/username"),
                ExternalLink(LinkType.PORTFOLIO, "https://drive.google.com/...")
            ),
            progress = 0.9f,
            paddingValues = PaddingValues(),
            onAddLink = {},
            onRemoveLink = {},
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LinksScreenEmptyPreview() {
    HsLinkTheme {
        LinksScreen(
            links = emptyList(),
            progress = 0.9f,
            paddingValues = PaddingValues(),
            onAddLink = {},
            onRemoveLink = {},
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}