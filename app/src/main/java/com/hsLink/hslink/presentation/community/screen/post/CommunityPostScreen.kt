package com.hsLink.hslink.presentation.community.screen.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkDialog
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.community.component.CommentInput
import com.hsLink.hslink.presentation.community.component.CommentItem
import com.hsLink.hslink.presentation.community.component.EmptyComment
import com.hsLink.hslink.presentation.community.component.PostContent
import com.hsLink.hslink.presentation.community.component.PostHeader
import com.hsLink.hslink.presentation.community.state.CommunityDetailState
import com.hsLink.hslink.presentation.community.viewmodel.CommunityViewModel


@Preview(showBackground = true)
@Composable
private fun CommunityPostScreenPreview() {
    HsLinkTheme {
        CommunityPostScreen(
            postId = 1,
            paddingValues = PaddingValues(),
            navigateUp = {},
            postDetailState = CommunityDetailState.Loading,
        )
    }
}

@Composable
fun CommunityPostRoute(
    postId: Int,
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    viewModel: CommunityViewModel = hiltViewModel(),
) {

    LaunchedEffect(postId) {
        viewModel.getPostDetail(postId)
    }

    val postDetailState by viewModel.postDetailState.collectAsState()

    CommunityPostScreen(
        postId = postId,
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        postDetailState = postDetailState,
    )
}

@Composable
fun CommunityPostScreen(
    postId: Int,
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    postDetailState: CommunityDetailState,
    modifier: Modifier = Modifier,
) {
    var commentText by remember { mutableStateOf("") }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showDeleteCommentDialog by remember { mutableStateOf(false) }
    var selectedCommentId by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(HsLinkTheme.colors.Common)
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HsLinkTheme.colors.Common)
        ) {
            HsLinkTopBar(
                title = {
                    Text(
                        text = "게시글",
                        color = HsLinkTheme.colors.Grey700,
                        style = HsLinkTheme.typography.title_20Strong
                    )
                },
                leftIcon = R.drawable.ic_community_post_leftarrow,
                onLeftIconClick = navigateUp,
                rightIconSecond = if (postDetailState is CommunityDetailState.Success && postDetailState.post.mine) {
                    R.drawable.ic_community_kebab
                } else {
                    null
                },
                onRightIconSecondClick = {
                    if (postDetailState is CommunityDetailState.Success && postDetailState.post.mine) {
                        showDeleteDialog = true
                    }
                }
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = HsLinkTheme.colors.Grey100
            )

            when (postDetailState) {
                is CommunityDetailState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is CommunityDetailState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = postDetailState.message,
                            color = Color.Red,
                            style = HsLinkTheme.typography.body_16Normal
                        )
                    }
                }

                is CommunityDetailState.Success -> {
                    val postDetail = postDetailState.post

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .imePadding()
                    ) {
                        item {
                            PostHeader(
                                authorName = postDetail.author,
                                authorMajor = postDetail.authorStatus,
                                boardType = "자유게시판",
                                timeAgo = postDetail.studentId,
                            )
                        }

                        item {
                            PostContent(
                                title = postDetail.title,
                                content = postDetail.body
                            )
                        }

                        item {
                            HorizontalDivider(
                                thickness = 8.dp,
                                color = HsLinkTheme.colors.Grey100
                            )
                        }

                        if (postDetail.comments.isEmpty()) {
                            item {
                                EmptyComment()
                            }
                        } else {
                            items(
                                items = postDetail.comments,
                                key = { it.id }
                            ) { comment ->
                                CommentItem(
                                    authorName = comment.commenter,
                                    timeAgo = comment.commenterStatus,
                                    content = comment.content,
                                    isMyComment = comment.mine,
                                    onDeleteClick = {
                                        selectedCommentId = comment.id
                                        showDeleteCommentDialog = true
                                    }
                                )

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = HsLinkTheme.colors.Grey100
                                )
                            }
                        }
                    }

                    CommentInput(
                        value = commentText,
                        onValueChange = { commentText = it },
                        onSendClick = {
                            // TODO: 댓글 전송 로직 (postId 사용)
                            commentText = ""
                        }
                    )
                }
            }
        }
    }

    if (showDeleteDialog && postDetailState is CommunityDetailState.Success && postDetailState.post.mine) {
        HsLinkDialog(
            title = "게시글을 삭제하시겠습니까?",
            message = "삭제된 게시글은 복구할 수 없습니다.",
            confirmText = "삭제하기",
            dismissText = "취소",
            onConfirm = {
                showDeleteDialog = false
                // TODO: 게시글 삭제 로직 (postId 사용)
                navigateUp()
            },
            onDismiss = {
                showDeleteDialog = false
            }
        )
    }

    if (showDeleteCommentDialog && selectedCommentId != null) {
        HsLinkDialog(
            title = "댓글을 삭제하시겠습니까?",
            message = "삭제된 댓글은 복구할 수 없습니다.",
            confirmText = "삭제하기",
            dismissText = "취소",
            onConfirm = {
                showDeleteCommentDialog = false
                // TODO: 댓글 삭제 로직 (selectedCommentId 사용)
                selectedCommentId = null
            },
            onDismiss = {
                showDeleteCommentDialog = false
                selectedCommentId = null
            }
        )
    }
}
