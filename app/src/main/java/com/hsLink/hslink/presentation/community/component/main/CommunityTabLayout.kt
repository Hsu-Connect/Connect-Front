package com.hsLink.hslink.presentation.community.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Preview(showBackground = true)
@Composable
private fun PreviewCommunityTabLayout() {
    HsLinkTheme {
        CommunityTabLayout(
            selectedTab = CommunityTab.Popular,
            onTabSelected = {}
        )
    }
}

enum class CommunityTab(val title: String) {
    Popular("인기글"),
    Free("자유게시판"),
    Promotion("홍보게시판"),
    Notice("공지")
}

@Composable
fun CommunityTabLayout(
    selectedTab: CommunityTab,
    onTabSelected: (CommunityTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = CommunityTab.entries

    HorizontalDivider(
        thickness = 1.dp,
        color = HsLinkTheme.colors.Grey100,
    )
    TabRow(
        selectedTabIndex = tabs.indexOf(selectedTab),
        modifier = modifier.fillMaxWidth(),
        contentColor = HsLinkTheme.colors.SkyBlue500,
        containerColor = HsLinkTheme.colors.Common,
        divider = {},
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[tabs.indexOf(selectedTab)]),
                color = HsLinkTheme.colors.SkyBlue500,
                height = 2.dp
            )
        }
    ) {
        tabs.forEach { tab ->
            Tab(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                Text(
                    text = tab.title,
                    style = HsLinkTheme.typography.title_16Strong,
                    color = if (selectedTab == tab) {
                        HsLinkTheme.colors.SkyBlue500
                    } else {
                        HsLinkTheme.colors.Grey200
                    }
                )
            }
        }
    }
}