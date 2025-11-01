package com.hsLink.hslink.presentation.community.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Composable
fun PostContent(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Text(
            text = title,
            color = HsLinkTheme.colors.Grey700,
            style = HsLinkTheme.typography.title_16Strong
        )

        Text(
            text = content,
            color = HsLinkTheme.colors.Grey600,
            style = HsLinkTheme.typography.body_14Normal,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PostContentPreview() {
    HsLinkTheme {
        PostContent(
            title = "추천 채용 한성 IT 추천 채용 공고 - 네이버 영업직 구합니다.",
            content = "제가 다니고 있는 한성 it에서 추천 채용이 올라와 공유드립니다. 이미지 첨고해주세요"
        )
    }
}