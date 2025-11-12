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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable
import com.hsLink.hslink.domain.model.community.CommunityPost
import com.hsLink.hslink.presentation.community.component.CommunityTab
import com.hsLink.hslink.presentation.community.component.CommunityTabLayout
import com.hsLink.hslink.presentation.community.viewmodel.CommunityViewModel
import com.hsLink.hslink.presentation.home.component.CommunityCardItem

@Preview(showBackground = true)
@Composable
private fun PreviewCommunityScreen() {
    HsLinkTheme {
    }
}

@Composable
fun CommunityRoute(
    paddingValues: PaddingValues,
    navigateWriteCommunity: () -> Unit,
    navigateToPost: (Int) -> Unit,
    viewModel: CommunityViewModel = hiltViewModel(),
) {
    val selectedTab by viewModel.selectedTab.collectAsState(initial = CommunityTab.Popular)
    val communityPosts = viewModel.communityPosts.collectAsLazyPagingItems()

    CommunityScreen(
        paddingValues = paddingValues,
        navigateWriteCommunity = navigateWriteCommunity,
        navigateToPost = navigateToPost,
        selectedTab = selectedTab,
        onTabSelected = viewModel::selectTab,
        posts = communityPosts
    )
}

@Composable
fun CommunityScreen(
    paddingValues: PaddingValues,
    navigateWriteCommunity: () -> Unit,
    navigateToPost: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedTab: CommunityTab,
    onTabSelected: (CommunityTab) -> Unit,
    posts: LazyPagingItems<CommunityPost>,
) {
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
            onTabSelected = onTabSelected
        )

        Box(
            modifier = Modifier.fillMaxHeight()
        ) {
            when (posts.loadState.refresh) {
                is LoadState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is LoadState.Error -> {
                    val error = posts.loadState.refresh as LoadState.Error
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "데이터 로드 오류 발생: ${error.error.localizedMessage}",
                            color = Color.Red
                        )
                    }
                }

                else -> {
                    if (posts.itemCount == 0) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "게시글이 존재하지 않습니다.", color = HsLinkTheme.colors.Grey500)
                        }
                    } else {
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
                                count = posts.itemCount,
                                key = { index -> posts[index]?.id ?: index }
                            ) { index ->
                                val post = posts[index]
                                post?.let {
                                    CommunityCardItem(
                                        userName = it.title,
                                        userMajor = it.summary,
                                        userId = it.studentId,
                                        userInfo = it.authorStatus,
                                        author = it.author,
                                        onClick = { navigateToPost(it.id) }
                                    )
                                }
                            }

                            if (posts.loadState.append is LoadState.Loading) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                        }
                    }
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