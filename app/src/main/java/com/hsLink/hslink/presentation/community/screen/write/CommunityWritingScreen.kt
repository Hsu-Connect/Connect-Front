package com.hsLink.hslink.presentation.community.screen.write

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkDialog
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.community.component.BoardSelectionField
import com.hsLink.hslink.presentation.community.component.BoardType
import com.hsLink.hslink.presentation.community.component.CommunityWriteButton

@Preview(showBackground = true)
@Composable
private fun CommunityWritingScreenPreview() {
    HsLinkTheme {
        CommunityWritingScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateToCommunity = {}
        )
    }
}

@Composable
fun CommunityWritingRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToCommunity: () -> Unit,
) {
    CommunityWritingScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        navigateToCommunity = navigateToCommunity
    )
}

@Composable
fun CommunityWritingScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToCommunity: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedBoardType by remember { mutableStateOf<BoardType?>(null) }
    var selectedBoardText by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    var isBoardExpanded by remember { mutableStateOf(false) }
    var isBoardFocused by remember { mutableStateOf(false) }
    var isTitleFocused by remember { mutableStateOf(false) }
    var isContentFocused by remember { mutableStateOf(false) }

    var showExitDialog by remember { mutableStateOf(false) }
    var showUploadDialog by remember { mutableStateOf(false) }

    val isDone = selectedBoardType != null &&
            title.isNotBlank() &&
            content.isNotBlank()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = HsLinkTheme.colors.Common)
            .padding(paddingValues)
            .imePadding()
    ) {
        HsLinkTopBar(
            modifier = Modifier
                .padding(start = 12.dp),
            title = {
                Text(
                    text = "글쓰기",
                    color = HsLinkTheme.colors.Grey600,
                    style = HsLinkTheme.typography.title_20Strong
                )
            },
            rightIconSecond = R.drawable.ic_community_write_close,
            onRightIconSecondClick = { showExitDialog = true }
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = HsLinkTheme.colors.Grey100
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .background(color = HsLinkTheme.colors.Common)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("게시판 선택 ")
                        withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                            append("*")
                        }
                    },
                    color = HsLinkTheme.colors.Grey700,
                    style = HsLinkTheme.typography.title_14Strong
                )

                BoardSelectionField(
                    selectedBoard = selectedBoardText,
                    isExpanded = isBoardExpanded,
                    onExpandedChange = { isBoardExpanded = it },
                    onBoardSelected = { board ->
                        selectedBoardType = board
                        selectedBoardText = board.displayName
                    },
                    isFocused = isBoardFocused,
                    onFocusChanged = { isBoardFocused = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("제목 ")
                        withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                            append("*")
                        }
                    },
                    color = HsLinkTheme.colors.Grey700,
                    style = HsLinkTheme.typography.title_14Strong
                )

                val maxTitleLength = 30

                HsLinkTextField(
                    value = title,
                    placeholder = "제목을 입력해주세요",
                    onValueChanged = {
                        if (it.length <= maxTitleLength) {
                            title = it
                        }
                    },
                    borderColor = if (isTitleFocused) {
                        HsLinkTheme.colors.DeepBlue500
                    } else {
                        HsLinkTheme.colors.Grey200
                    },
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { isTitleFocused = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("글 작성 ")
                        withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                            append("*")
                        }
                    },
                    color = HsLinkTheme.colors.Grey700,
                    style = HsLinkTheme.typography.title_14Strong
                )

                val maxContentLength = 500

                HsLinkTextField(
                    value = content,
                    placeholder = "내용을 입력해주세요",
                    onValueChanged = {
                        if (it.length <= maxContentLength) {
                            content = it
                        }
                    },
                    borderColor = if (isContentFocused) {
                        HsLinkTheme.colors.DeepBlue500
                    } else {
                        HsLinkTheme.colors.Grey200
                    },
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { isContentFocused = it },
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "${content.length}/$maxContentLength",
                    color = if (content.length >= maxContentLength) {
                        HsLinkTheme.colors.Red500
                    } else {
                        HsLinkTheme.colors.Grey400
                    },
                    style = HsLinkTheme.typography.caption_12Normal,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }

        CommunityWriteButton(
            isDone = isDone,
            onClick = {
                if (isDone) {
                    showUploadDialog = true
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }

    if (showExitDialog) {
        HsLinkDialog(
            title = "글쓰기를 종료하시겠습니까?",
            message = "작성 중인 내용이 저장되지 않습니다.",
            confirmText = "나가기",
            dismissText = "취소하기",
            onConfirm = {
                showExitDialog = false
                navigateUp()
            },
            onDismiss = {
                showExitDialog = false
            }
        )
    }

    if (showUploadDialog) {
        HsLinkDialog(
            title = "게시글을 업로드하시겠습니까?",
            message = "작성한 내용이 게시판에 공개됩니다.",
            confirmText = "업로드하기",
            dismissText = "취소하기",
            onConfirm = {
                showUploadDialog = false
                navigateToCommunity()
            },
            onDismiss = {
                showUploadDialog = false
            }
        )
    }
}