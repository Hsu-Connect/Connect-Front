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
private fun MyPageCardItemPreview() {
    HsLinkTheme {
        MyPageCardItemContainer(
            text = "게시글",
            items = listOf(
                MyPageItemData(id = "1", title = "나의 게시글", route = "/posts"),
                MyPageItemData(id = "2", title = "나의 댓글", route = "/settings")
            ),
            onItemClick = { item ->
            }
        )
    }
}

data class MyPageItemData(
    val id: String,
    val title: String,
    val route: String,
)

@Composable
fun MyPageCardItemContainer(
    items: List<MyPageItemData>, // 아이템 리스트 파라미터로
    onItemClick: (MyPageItemData) -> Unit, // 클릭 이벤트 외부에서
    modifier: Modifier = Modifier,
    text : String
)
{
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                Text(
                    text = text,
                    color = Black,
                    style = HsLinkTheme.typography.title_14Strong,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )


                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items.forEach { item ->
                        MyPageCardItem(
                            text = item.title,
                            onClick = { onItemClick(item) }
                        )
                    }
                }
            }
}

@Composable
fun MyPageCardItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = HsLinkTheme.colors.Grey200
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = HsLinkTheme.colors.Grey500,
                style = HsLinkTheme.typography.btm_M
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_mypage_item_lefrarrow),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}