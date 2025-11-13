package com.hsLink.hslink.presentation.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.home.component.HomeBottomContent
import com.hsLink.hslink.presentation.home.component.HomeCardItem
import com.hsLink.hslink.presentation.home.component.HomeCarousel
import com.hsLink.hslink.presentation.home.component.HomeContentField
import com.hsLink.hslink.presentation.home.component.HomePost
import com.hsLink.hslink.presentation.home.component.HomePostContainer
import com.hsLink.hslink.presentation.home.component.HomePromotionPost
import com.hsLink.hslink.presentation.home.viewmodel.HomeViewModel

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HsLinkTheme {
        HomeScreen(
            paddingValues = PaddingValues(),
            popularPosts = listOf(),
            promotionPosts = listOf(),
            onPopularClick = { },
            onPromotionClick = { }
        )
    }
}

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    val popularPosts = state.postPopular.map { post ->
        HomePost(
            id = post.id.toString(),
            title = post.title
        )
    }

    val promotionPosts = state.postPromotion.map { post ->
        HomePromotionPost(
            id = post.id,
            title = post.title,
            summary = post.summary,
            author = post.author,
            studentId = post.studentId
        )
    }


    HsLinkTheme {
        HomeScreen(
            paddingValues = paddingValues,
            popularPosts = popularPosts,
            promotionPosts = promotionPosts,
            onPopularClick = { },
            onPromotionClick = { },
        )
    }
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    popularPosts: List<HomePost>,
    promotionPosts: List<HomePromotionPost>,
    onPopularClick: (Int) -> Unit,
    onPromotionClick: (HomePromotionPost) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = HsLinkTheme.colors.Common)
            .padding(paddingValues),
    ) {
        item {
            HsLinkTopBar(
                modifier = Modifier
                    .padding(start = 12.dp),
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.img_home_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .height(48.dp)
                            .width(92.dp),
                        contentScale = ContentScale.FillBounds,
                    )
                },
                rightIconFirst = null,
                rightIconSecond = null,
                leftIcon = null
            )
        }

        item {
            HomeCarousel(
                pagerImages = listOf(
                    "https://www.hansung.ac.kr/sites/hansung/images/main/slide_main/24-003.jpg",
                    "https://www.hansung.ac.kr/sites/hansung/images/main/slide_main/24-%EC%A0%84%EA%B5%AC.jpg",
                    "https://www.hansung.ac.kr/sites/hansung/images/main/slide_main/%EC%84%B1%EA%B9%9403.jpg",
                    "https://www.hansung.ac.kr/sites/hansung/images/main/slide_main/%EC%84%B1%EA%B9%9401.jpg",
                    "https://www.hansung.ac.kr/sites/hansung/images/main/slide_main/24-001.jpg"
                ),
            )
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))

            HomeContentField(
                mainText = "멘토링 신청",
                subText = "한성선배님께 멘토링을\n신청해보세요!",
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            HomeCardItem(
                userName = "송효재",
                userMajor = "회계재무경영",
                userId = "20",
                userInfo = "구직중,졸업",
                routeId = 0,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))

            HomeContentField(
                mainText = "인기 게시글",
                subText = "실시간 인기 게시글을\n확인해보세요!",
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            HomePostContainer(
                posts = popularPosts,
                onPostClick = { post ->
                    onPopularClick(post.id.toInt())
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))

            HomeContentField(
                mainText = "최신 홍보 게시글",
                subText = "실시간 홍보 게시글을\n확인해보세요!",
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))

            LazyRow(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(promotionPosts) { post ->
                    HomeCardItem(
                        userName = post.title,
                        userMajor = post.summary,
                        userInfo = post.author,
                        userId = post.studentId,
                        routeId = post.id,
                        modifier = Modifier.width(250.dp)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(36.dp))

            HorizontalDivider(
                thickness = 1.dp,
                color = HsLinkTheme.colors.Grey100
            )
        }

        item {
            HomeBottomContent(
                onClick = { },
            )
        }

        item {
            HorizontalDivider(
                thickness = 1.dp,
                color = HsLinkTheme.colors.Grey100
            )
        }

    }

}
