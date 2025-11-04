package com.hsLink.hslink.presentation.mypage.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Preview(showBackground = true)
@Composable
private fun MyPageDetailItemContentPreview() {
    HsLinkTheme {
        MyPageDetailItemContent(
            name ="송효재",
            title = "21학번 회계재무경영",
            subtitle = "구직 중 · 재직 중 · 졸업",
            onClick = { }
        )
    }
}

data class MyPageDetailItemData(
    val id: String,
    val name : String,
    val title: String,
    val subtitle: String,
    val route: String,
)

@Composable
fun MyPageDetailItemContent(
    name : String,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = HsLinkTheme.colors.SkyBlue100
        ),
        border = BorderStroke(
            width = 1.dp,
            color = HsLinkTheme.colors.Grey200
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = name,
                    color = Black,
                    style = HsLinkTheme.typography.title_20Strong
                )
                
                Text(
                    text = title,
                    color = Black,
                    style = HsLinkTheme.typography.body_14Normal
                )
                Text(
                    text = subtitle,
                    color = HsLinkTheme.colors.Grey400,
                    style = HsLinkTheme.typography.btm_M
                )
            }

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_mypage_item_lefrarrow),
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}