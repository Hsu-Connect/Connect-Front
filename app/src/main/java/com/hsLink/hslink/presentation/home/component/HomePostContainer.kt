package com.hsLink.hslink.presentation.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable


@Preview(showBackground = true)
@Composable
private fun HomePostContainerPreview() {
    HsLinkTheme {
        HomePostContainer(
            posts = listOf(
                HomePost(id = "1", title = "2026 카카오 신입 공채", route = "route1"),
                HomePost(id = "2", title = "선배가 알려주는 이력서 꿀팁", route = "route2")
            ),
            onPostClick = {}
        )
    }
}

data class HomePost(
    val id: String,
    val title: String,
    val route: String,
)

@Composable
fun HomePostContainer(
    posts: List<HomePost>,
    onPostClick: (HomePost) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = HsLinkTheme.colors.Grey100,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        posts.forEachIndexed { index, post ->
            HomePostContent(
                postTitle = post.title,
                onClick = { onPostClick(post) }
            )

            if (index < posts.lastIndex) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = HsLinkTheme.colors.Grey100
                )
            }
        }
    }
}

@Composable
private fun HomePostContent(
    postTitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = onClick)
            .padding(vertical = 13.dp, horizontal = 16.dp)
    ) {
        Text(
            text = postTitle,
            color = HsLinkTheme.colors.Grey700,
            style = HsLinkTheme.typography.title_16Strong
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_post_arrow),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}