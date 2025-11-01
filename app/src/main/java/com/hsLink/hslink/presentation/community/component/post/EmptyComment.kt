package com.hsLink.hslink.presentation.community.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Composable
fun EmptyComment(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "아직 댓글이 없어요",
            color = HsLinkTheme.colors.Grey700,
            style = HsLinkTheme.typography.title_16Strong
        )

        Text(
            text = "말을 걸어 대화를 시작하세요.",
            color = HsLinkTheme.colors.Grey500,
            style = HsLinkTheme.typography.body_14Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyCommentPreview() {
    HsLinkTheme {
        EmptyComment()
    }
}