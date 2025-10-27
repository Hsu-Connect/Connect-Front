package com.hsLink.hslink.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Preview(showBackground = true)
@Composable
private fun HomeCarouselPreview() {
    HsLinkTheme {
        HomeCarousel(
            pagerImages = listOf(
                "https://example.com/image1.jpg",
                "https://example.com/image2.jpg",
                "https://example.com/image3.jpg"
            ),
            modifier = Modifier
        )
    }
}

@Composable
fun HomeCarousel(
    pagerImages: List<String>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { pagerImages.size })

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) { page ->
            AsyncImage(
                model = pagerImages[page],
                contentDescription = "Pager Image $page",
                contentScale = ContentScale.FillBounds,
            )
        }

        HomeCarouselIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp, end = 16.dp)
        )
    }
}

@Composable
private fun HomeCarouselIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = HsLinkTheme.colors.Grey400,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "${pagerState.currentPage + 1} / ${pagerState.pageCount}",
            color = HsLinkTheme.colors.Grey100,
            style = HsLinkTheme.typography.caption_12Normal
        )
    }
}