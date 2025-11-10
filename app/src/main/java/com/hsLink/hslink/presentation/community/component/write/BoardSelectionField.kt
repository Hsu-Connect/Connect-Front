package com.hsLink.hslink.presentation.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

enum class BoardType(val displayName: String) {
    FREE("자유게시판"),
    PROMOTION("홍보게시판"),
    NOTICE("공지게시판")
}

@Preview(showBackground = true)
@Composable
private fun BoardSelectionFieldPreview() {
    HsLinkTheme {
        BoardSelectionField(
            selectedBoard = "자유게시판",
            isExpanded = false,
            onExpandedChange = {},
            onBoardSelected = {},
            isFocused = false,
            onFocusChanged = {},
        )
    }
}

@Composable
fun BoardSelectionField(
    selectedBoard: String,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onBoardSelected: (BoardType) -> Unit,
    isFocused: Boolean,
    onFocusChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        HsLinkTextField(
            value = selectedBoard,
            placeholder = "게시판을 선택해주세요",
            onValueChanged = { },
            borderColor = if (isFocused || isExpanded) {
                HsLinkTheme.colors.DeepBlue500
            } else {
                HsLinkTheme.colors.Grey200
            },
            backgroundColor = HsLinkTheme.colors.Common,
            onFocusChanged = onFocusChanged,
            leadingIconRes = if(!isExpanded) R.drawable.ic_community_arrow_down else R.drawable.ic_community_arrow_up,
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onExpandedChange(!isExpanded)
                }
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { onExpandedChange(false) },
            modifier = Modifier
                .fillMaxWidth()
                .background(HsLinkTheme.colors.Common)
                .padding(horizontal = 16.dp)
        ) {
            BoardType.entries.forEach { board ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = board.displayName,
                            color = HsLinkTheme.colors.Grey700,
                            style = HsLinkTheme.typography.body_14Normal
                        )
                    },
                    onClick = {
                        onBoardSelected(board)
                        onExpandedChange(false)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}