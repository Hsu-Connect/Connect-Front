package com.hsLink.hslink.presentation.mypage.component.profile

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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Preview(showBackground = true)
@Composable
private fun CareerCardPreview() {
    HsLinkTheme {
        CareerCard(
            name = "투썸플레이스",
            title = "영업",
            dateRange = "2024.02 ~ 2024.10",  // ← 변경
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
fun CareerCard(
    name: String,
    title: String,
    dateRange: String,  // ← subtitle 대신 dateRange
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = HsLinkTheme.colors.Common
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
                    text = name,              // 투썸플레이스
                    color = Black,
                    style = HsLinkTheme.typography.title_20Strong
                )

                Text(
                    text = title,            // 영업
                    color = Black,
                    style = HsLinkTheme.typography.body_14Normal
                )

                Text(
                    text = dateRange,        // 2024.02 ~ 2024.10
                    color = HsLinkTheme.colors.Grey400,
                    style = HsLinkTheme.typography.btm_M
                )
            }

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_mypage_item_lefrarrow),
                contentDescription = null,
                tint = Black
            )
        }
    }
}