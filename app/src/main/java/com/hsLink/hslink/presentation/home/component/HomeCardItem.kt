package com.hsLink.hslink.presentation.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Preview(showBackground = true)
@Composable
private fun PreviewHomeCardItem() {
    HsLinkTheme {
        HomeCardItem(
            userName = "John Doe",
            userMajor = "Computer Science",
            userId = "20학번",
            userInfo = "Senior at XYZ University",
            routeId = 1,
            modifier = Modifier
        )
    }
}

@Immutable
data class HomePromotionPost(
    val id: Int,
    val title: String,
    val summary: String,
    val author: String,
    val studentId: String,
)
@Composable
fun HomeCardItem(
    userName: String,
    userMajor: String,
    userInfo: String,
    userId: String,
    routeId: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = HsLinkTheme.colors.Grey100,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = userName,
                color = HsLinkTheme.colors.Grey700,
                style = HsLinkTheme.typography.title_16Strong,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = userMajor,
                color = HsLinkTheme.colors.Grey700,
                style = HsLinkTheme.typography.body_14Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = userId,
                    color = HsLinkTheme.colors.Grey400,
                    style = HsLinkTheme.typography.caption_12Normal, maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = userInfo,
                    color = HsLinkTheme.colors.Grey400,
                    style = HsLinkTheme.typography.caption_12Normal, maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_arrow_right),
            contentDescription = null,
            tint = HsLinkTheme.colors.Grey200
        )
    }
}