package com.hsLink.hslink.presentation.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable

@Composable
fun CommentInput(
    value: String,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(HsLinkTheme.colors.Grey100)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .border(
                width = 1.dp,
                color = HsLinkTheme.colors.Grey200,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f)
                .background(color = HsLinkTheme.colors.Grey100),
            textStyle = HsLinkTheme.typography.body_14Normal.copy(
                color = HsLinkTheme.colors.Grey700
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = "댓글을 입력해주세요",
                        color = HsLinkTheme.colors.Grey400,
                        style = HsLinkTheme.typography.body_14Normal
                    )
                }
                innerTextField()
            }
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_community_post_send),
            contentDescription = "댓글 전송",
            tint = if (value.isNotBlank()) {
                HsLinkTheme.colors.DeepBlue500
            } else {
                HsLinkTheme.colors.Grey300
            },
            modifier = Modifier
                .noRippleClickable(
                    enabled = value.isNotBlank(),
                    onClick = onSendClick
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentInputPreview() {
    HsLinkTheme {
        CommentInput(
            value = "",
            onValueChange = {},
            onSendClick = {}
        )
    }
}