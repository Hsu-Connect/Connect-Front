package com.hsLink.hslink.presentation.search.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.domain.model.search.LinkEntity

@Composable
fun LinkCard(
    links: List<LinkEntity>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "자기소개 링크",
            style = HsLinkTheme.typography.title_16Strong,
            color = HsLinkTheme.colors.Grey700,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = HsLinkTheme.colors.Common
            ),
            border = BorderStroke(
                width = 1.dp,
                color = HsLinkTheme.colors.Grey200
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                links.forEachIndexed { index, link ->
                    LinkItem(link = link)
                    if (index < links.size - 1) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun LinkItem(
    link: LinkEntity,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = getLinkTypeText(link.type),
            style = HsLinkTheme.typography.body_16Strong,
            color = HsLinkTheme.colors.Grey700
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = link.url,
            style = HsLinkTheme.typography.body_16Strong,
            color = HsLinkTheme.colors.DeepBlue500
        )
    }
}

private fun getLinkTypeText(type: String): String {
    return when (type) {
        "LINKEDIN" -> "LinkedIn"
        "GITHUB" -> "GitHub"
        "INSTAGRAM" -> "Instagram"
        "BLOG" -> "블로그"
        else -> "기타"
    }
}