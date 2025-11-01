package com.hsLink.hslink.presentation.community.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable

@Composable
fun CommentItem(
    authorName: String,
    timeAgo: String,
    content: String,
    isMyComment: Boolean,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = authorName,
                    color = HsLinkTheme.colors.Grey700,
                    style = HsLinkTheme.typography.title_14Strong
                )

                Text(
                    text = timeAgo,
                    color = HsLinkTheme.colors.Grey400,
                    style = HsLinkTheme.typography.caption_12Normal
                )
            }

            Text(
                text = content,
                color = HsLinkTheme.colors.Grey600,
                style = HsLinkTheme.typography.body_14Normal
            )
        }

        if (isMyComment) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_community_kebab),
                contentDescription = "댓글 삭제",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(24.dp)
                    .noRippleClickable(onClick = onDeleteClick)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentItemPreview() {
    HsLinkTheme {
        CommentItem(
            authorName = "송효재",
            timeAgo = "21학번",
            content = "자소서 어떻게 작성하셨나요? 주로 보는 인재상이 있는지 궁금합니다.",
            isMyComment = true,
            onDeleteClick = {}
        )
    }
}