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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable
import com.hsLink.hslink.domain.model.search.LinkItemEntity
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen

@Composable
fun LinksScreen(
    linkList: List<LinkItemEntity>,
    progress: Float,
    paddingValues: PaddingValues,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    onAddLinkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OnboardingScreen(
        title = "외부 링크를 등록해보세요",
        progress = progress,
        subtitle = "블로그, 깃허브, 포트폴리오 등을 등록하면 동문들과 더 쉽게 연결될 수 있어요.",
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = true,
        nextButtonLabel = "완료",
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (linkList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(linkList) { link ->
                            LinkItem(link)
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = HsLinkTheme.colors.Common,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = HsLinkTheme.colors.Grey100,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(vertical = 40.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "외부 링크를 추가해보세요",
                        style = HsLinkTheme.typography.title_16Strong,
                        color = HsLinkTheme.colors.Grey700
                    )
                    Text(
                        text = "블로그, 깃허브, 포트폴리오 등을 등록할 수 있어요.",
                        style = HsLinkTheme.typography.body_14Normal,
                        color = HsLinkTheme.colors.Grey500
                    )
                    LinkAddButton(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = onAddLinkClick,
                        label = "링크 추가하기"
                    )
                }
            }
        }
    }
}

@Composable
private fun LinkItem(link: LinkItemEntity, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = HsLinkTheme.colors.Grey100,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        link.type?.let {
            Text(
                text = it.label,
                style = HsLinkTheme.typography.title_14Strong,
                color = HsLinkTheme.colors.Grey700
            )
        }
        Text(
            text = link.url,
            style = HsLinkTheme.typography.body_14Normal,
            color = HsLinkTheme.colors.SkyBlue500
        )
    }
}

@Composable
private fun LinkAddButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    isEnabled: Boolean = true,
) {
    Row(
        modifier = modifier
            .background(
                color = HsLinkTheme.colors.Grey100,
                shape = RoundedCornerShape(8.dp)
            )
            .noRippleClickable(onClick = onClick, enabled = isEnabled)
            .padding(horizontal = 68.dp, vertical = 9.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_onboarding_plus),
            contentDescription = "링크 추가하기",
            tint = HsLinkTheme.colors.Grey500
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = HsLinkTheme.colors.Grey500,
            style = HsLinkTheme.typography.btm_M
        )
    }
}
