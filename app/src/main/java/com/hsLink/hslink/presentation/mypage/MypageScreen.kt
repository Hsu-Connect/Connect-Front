package com.hsLink.hslink.presentation.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.mypage.component.MyPageCardItemContainer
import com.hsLink.hslink.presentation.mypage.component.MyPageDetailItemContent
import com.hsLink.hslink.presentation.mypage.component.MyPageItemData

@Preview(showBackground = true)
@Composable
private fun MypageScreenPreview() {
    MypageScreen(paddingValues = PaddingValues())
}

@Composable
fun MypageRoute(
    paddingValues: PaddingValues,
) {
    MypageScreen(paddingValues)
}

@Composable
fun MypageScreen(
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
            MyPageDetailItemContent(
                name = "송효재",
                title = "21학번 회계재무경영",
                subtitle = "구직 중 · 재직 중 · 졸업",
                onClick = { }
            )
        }

        item {
            MyPageCardItemContainer(
                text = "마이페이지",
                items = listOf(
                    MyPageItemData(id = "1", title = "나의 게시글", route = "/posts"),
                    MyPageItemData(id = "2", title = "설정", route = "/settings")
                ),
                onItemClick = { item ->
                    // 클릭 처리
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            MyPageCardItemContainer(
                text = "계정",
                items = listOf(
                    MyPageItemData(id = "3", title = "로그아웃", route = "/posts"),
                    MyPageItemData(id = "4", title = "설정", route = "/settings")
                ),
                onItemClick = { item ->
                    // 클릭 처리
                }
            )
        }
    }
    }