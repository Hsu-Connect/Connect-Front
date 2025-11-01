package com.hsLink.hslink.presentation.community.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Composable
fun PostHeader(
    authorName: String,
    authorMajor: String,
    boardType: String,
    timeAgo: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "$authorName",
                color = HsLinkTheme.colors.Grey700,
                style = HsLinkTheme.typography.caption_12Normal
            )

            Text(
                text = boardType,
                color = HsLinkTheme.colors.SkyBlue500,
                style = HsLinkTheme.typography.caption_12Normal
            )
        }

        Text(
            text = "$timeAgo · $authorMajor",
            color = HsLinkTheme.colors.Grey400,
            style = HsLinkTheme.typography.caption_12Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PostHeaderPreview() {
    HsLinkTheme {
        PostHeader(
            authorName = "송효재",
            authorMajor = "재직중",
            boardType = "(작성자)",
            timeAgo = "21학번"
        )
    }
}