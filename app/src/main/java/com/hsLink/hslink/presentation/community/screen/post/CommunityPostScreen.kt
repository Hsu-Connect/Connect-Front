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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkDialog
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.data.local.Comment
import com.hsLink.hslink.data.local.PostDetail
import com.hsLink.hslink.presentation.community.component.CommentInput
import com.hsLink.hslink.presentation.community.component.CommentItem
import com.hsLink.hslink.presentation.community.component.EmptyComment
import com.hsLink.hslink.presentation.community.component.PostContent
import com.hsLink.hslink.presentation.community.component.PostHeader


@Preview(showBackground = true)
@Composable
private fun CommunityPostScreenPreview() {
    HsLinkTheme {
        CommunityPostScreen(
            paddingValues = PaddingValues(),
            navigateUp = {}
        )
    }
}

@Composable
fun CommunityPostRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
) {
    CommunityPostScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp
    )
}

@Composable
fun CommunityPostScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val postDetail = remember {
        PostDetail(
            id = "1",
            authorName = "송효재",
            authorMajor = "재직중",
            boardType = "자유게시판",
            timeAgo = "21학번",
            title = "추천 채용 한성 IT 추천 채용 공고 - 네이버 영업직 구합니다.",
            content = "제가 다니고 있는 한성 it에서 추천 채용이 올라와 공유드립니다. 이미지 첨고해주세요",
            isMyPost = true,
            comments = listOf(
                Comment(
                    id = "1",
                    authorName = "송효재",
                    timeAgo = "21학번",
                    content = "자기소개서 어떻게 작성하셨나요? 주로 보는 인재상이 있는지 궁금합니다.",
                    isMyComment = true
                ),
                Comment(
                    id = "2",
                    authorName = "김철수",
                    timeAgo = "20학번",
                    content = "좋은 정보 감사합니다!",
                    isMyComment = false
                )
            )
        )
    }

    var commentText by remember { mutableStateOf("") }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showDeleteCommentDialog by remember { mutableStateOf(false) }
    var selectedCommentId by remember { mutableStateOf<String?>(null) }

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
                rightIconSecond = if (postDetail.isMyPost) R.drawable.ic_community_kebab else null,
                onRightIconSecondClick = {
                    if (postDetail.isMyPost) {
                        showDeleteDialog = true
                    }
                }
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = HsLinkTheme.colors.Grey100
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .imePadding()
            ) {
                item {
                    PostHeader(
                        authorName = postDetail.authorName,
                        authorMajor = postDetail.authorMajor,
                        boardType = postDetail.boardType,
                        timeAgo = postDetail.timeAgo
                    )
                }

                item {
                    PostContent(
                        title = postDetail.title,
                        content = postDetail.content
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
                            authorName = comment.authorName,
                            timeAgo = comment.timeAgo,
                            content = comment.content,
                            isMyComment = comment.isMyComment,
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
                    // TODO: 댓글 전송 로직
                    commentText = ""
                }
            )
        }
    }

    if (showDeleteDialog) {
        HsLinkDialog(
            title = "게시글을 삭제하시겠습니까?",
            message = "삭제된 게시글은 복구할 수 없습니다.",
            confirmText = "삭제하기",
            dismissText = "취소",
            onConfirm = {
                showDeleteDialog = false
                // TODO: 게시글 삭제 로직
                navigateUp()
            },
            onDismiss = {
                showDeleteDialog = false
            }
        )
    }

    if (showDeleteCommentDialog) {
        HsLinkDialog(
            title = "댓글을 삭제하시겠습니까?",
            message = "삭제된 댓글은 복구할 수 없습니다.",
            confirmText = "삭제하기",
            dismissText = "취소",
            onConfirm = {
                showDeleteCommentDialog = false
                // TODO: 댓글 삭제 로직 (selectedCommentId 사용)
            },
            onDismiss = {
                showDeleteCommentDialog = false
                selectedCommentId = null
            }
        )
    }
}