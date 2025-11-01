package com.hsLink.hslink.presentation.community.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable
import com.hsLink.hslink.presentation.community.component.CommunityTab
import com.hsLink.hslink.presentation.community.component.CommunityTabLayout
import com.hsLink.hslink.presentation.home.component.CommunityCardItem

data class CommunityPost(
    val id: String,
    val userName: String,
    val userMajor: String,
    val userInfo: String,
)

@Preview(showBackground = true)
@Composable
private fun PreviewCommunityScreen() {
    HsLinkTheme {
        CommunityScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateWriteCommunity = {},
            onClick = {},
            navigateToPost = {}
        )
    }
}

@Composable
fun CommunityRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateWriteCommunity: () -> Unit,
    navigateToPost: (String) -> Unit,
) {
    CommunityScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        navigateWriteCommunity = navigateWriteCommunity,
        onClick = {},
        navigateToPost = navigateToPost
    )
}

@Composable
fun CommunityScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateWriteCommunity: () -> Unit,
    onClick: () -> Unit,
    navigateToPost: (String) -> Unit,
    modifier: Modifier = Modifier,

    ) {
    var selectedTab by remember { mutableStateOf(CommunityTab.Popular) }

    val posts = remember(selectedTab) {
        when (selectedTab) {
            CommunityTab.Popular -> listOf(
                CommunityPost("1", "인기글 작성자1", "컴퓨터공학과", "인기글 내용입니다"),
                CommunityPost("2", "인기글 작성자2", "경영학과", "좋아요가 많은 글"),
                CommunityPost("3", "인기글 작성자3", "디자인학과", "핫한 글입니다"),
            )

            CommunityTab.Free -> listOf(
                CommunityPost("4", "자유 작성자1", "전자공학과", "자유게시판 글1"),
                CommunityPost("5", "자유 작성자2", "수학과", "자유게시판 글2"),
                CommunityPost("6", "자유 작성자3", "물리학과", "자유게시판 글3"),
            )

            CommunityTab.Promotion -> listOf(
                CommunityPost("7", "홍보 작성자1", "마케팅학과", "동아리 홍보합니다"),
                CommunityPost("8", "홍보 작성자2", "광고홍보학과", "행사 알림"),
                CommunityPost("9", "홍보 작성자3", "경제학과", "스터디 모집"),
            )

            CommunityTab.Notice -> listOf(
                CommunityPost("10", "관리자1", "학생처", "중요 공지사항"),
                CommunityPost("11", "관리자2", "교무처", "학사 일정 안내"),
                CommunityPost("12", "관리자3", "총학생회", "필독 공지"),
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = HsLinkTheme.colors.Common)
            .padding(paddingValues)
    ) {
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

        CommunityTabLayout(
            selectedTab = selectedTab,
            onTabSelected = { tab ->
                selectedTab = tab
            }
        )

        Box(
            modifier = Modifier.fillMaxHeight()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 80.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = posts,
                    key = { it.id }
                ) { post ->
                    CommunityCardItem(
                        userName = post.userName,
                        userMajor = post.userMajor,
                        userInfo = post.userInfo,
                        onClick = { navigateToPost(post.id) }
                    )
                }
            }

            CommunityFloatingButton(
                onClick = navigateWriteCommunity,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
private fun CommunityFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(6.dp)
            )
            .background(color = HsLinkTheme.colors.Common, shape = RoundedCornerShape(6.dp))
            .border(
                width = 1.dp,
                color = HsLinkTheme.colors.Grey200,
                shape = RoundedCornerShape(6.dp)
            )
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 9.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_community_post),
            contentDescription = null,
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "글쓰기",
            color = HsLinkTheme.colors.Grey500,
            style = HsLinkTheme.typography.btm_M,

            )

    }

}