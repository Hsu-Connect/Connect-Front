package com.hsLink.hslink.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.home.component.HomeBottomContent
import com.hsLink.hslink.presentation.home.component.HomeCardItem
import com.hsLink.hslink.presentation.home.component.HomeCarousel
import com.hsLink.hslink.presentation.home.component.HomeContentField
import com.hsLink.hslink.presentation.home.component.HomePost
import com.hsLink.hslink.presentation.home.component.HomePostContainer

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HsLinkTheme {
        HomeScreen(paddingValues = PaddingValues())
    }
}

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
) {
    HsLinkTheme {
        HomeScreen(paddingValues)
    }
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
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
                modifier = Modifier,
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
                    "https://cdn.edujin.co.kr/news/photo/202209/39788_81397_67.png",
                    "image2",
                    "image3"
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
                userInfo = "구직중,졸업",
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
                posts = listOf(
                    HomePost(
                        id = "1",
                        title = "2026 카카오 신입 공채",
                        route = "route1"
                    ),
                    HomePost(
                        id = "2",
                        title = "선배가 알려주는 이력서 꿀팁",
                        route = "route2"
                    ),
                    HomePost(
                        id = "3",
                        title = "선배가 알려주는 이력서 꿀팁",
                        route = "route3"
                    ),
                    HomePost(
                        id = "4",
                        title = "선배가 알려주는 이력서 꿀팁",
                        route = "route4"
                    ),
                    HomePost(
                        id = "5",
                        title = "선배가 알려주는 이력서 꿀팁",
                        route = "route5"
                    )

                ),
                onPostClick = {},
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))

            HomeContentField(
                mainText = "최신 홍보 게시글",
                subText = "실시간 홍보 게시물을\n확인해보세요!",
            )
        }

        item {
            HomeCardItem(
                userName = "한성대학생이 카카오에 취직하는 가장 확실한 방법을 알려드립니다.",
                userMajor = "한성대학교에 다니는 후배들이 취업고민이 많을 것 같은디,테스트용입니다",
                userInfo = "08학번 성규현",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
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
