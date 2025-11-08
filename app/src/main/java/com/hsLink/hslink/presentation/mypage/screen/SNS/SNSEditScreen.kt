package com.hsLink.hslink.presentation.mypage.screen.SNS

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButton
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.mypage.component.career.UnsavedChangesDialog

@Preview(showBackground = true)
@Composable
private fun SNSEditScreenPreview() {
    HsLinkTheme {
        SNSEditScreen(
            paddingValues = PaddingValues(),
            onBackClick = { },
            onCloseClick = { },
            onSaveClick = { }
        )
    }
}

@Composable
fun SNSEditRoute(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    SNSEditScreen(
        paddingValues = paddingValues,
        onBackClick = { navController.popBackStack() },
        onCloseClick = { navController.popBackStack() },
        onSaveClick = {
            // 저장 로직 후 이전 화면으로
            navController.popBackStack()
        }
    )
}


@Composable
fun SNSEditScreen(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // State 관리
    var selectedLinkType by remember { mutableStateOf("기타") }
    var url by remember { mutableStateOf("https://www.instagram.com/02_sing_song/") }
    var isUrlFocused by remember { mutableStateOf(false) }

    var showExitDialog by remember { mutableStateOf(false) }  // ← 추가

    // 변경사항이 있는지 체크하는 함수
    fun hasUnsavedChanges(): Boolean {
        return selectedLinkType != "기타" ||
                url != "https://www.instagram.com/02_sing_song/"
    }

    // 나가기 처리 함수
    fun handleExit() {
        if (hasUnsavedChanges()) {
            showExitDialog = true
        } else {
            onBackClick()
        }
    }

    val isFormValid = selectedLinkType.isNotEmpty() && url.isNotEmpty()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = HsLinkTheme.colors.Common)
            .padding(paddingValues),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            HsLinkTopBar(
                title = {
                    Text(
                        text = "외부 링크 수정하기",
                        style = HsLinkTheme.typography.title_20Strong
                    )
                },
                leftIcon = R.drawable.ic_topbar_arrowleft,
                rightIconFirst = R.drawable.ic_topbar_close,
                onLeftIconClick = { handleExit() },     // ← 수정
                onRightIconFirstClick = { handleExit() } // ← 수정
            )
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "링크 등록",
                    style = HsLinkTheme.typography.title_20Strong,
                    color = HsLinkTheme.colors.Grey700
                )

                Text(
                    text = "나중에 멘토링 할 때 기본 정보로 활용됩니다.",
                    style = HsLinkTheme.typography.body_14Normal,
                    color = HsLinkTheme.colors.Grey500
                )
            }
        }

// 링크 유형 구분
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(
                        text = "링크 유형 구분",
                        color = HsLinkTheme.colors.Grey700,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                    Text(
                        text = " *",
                        color = Color.Red,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                }

                var expanded by remember { mutableStateOf(false) }
                val linkTypes = listOf("기타", "링크드인", "인스타그램", "깃허브", "노션", "구글 드라이브")

                Box {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = !expanded },
                        border = BorderStroke(1.dp, HsLinkTheme.colors.Grey200),
                        colors = CardDefaults.cardColors(
                            containerColor = HsLinkTheme.colors.Common
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = selectedLinkType,
                                style = HsLinkTheme.typography.body_14Normal,
                                color = HsLinkTheme.colors.Grey700
                            )

//                            Icon(
//                                imageVector = ImageVector.vectorResource(R.drawable.ic_dropdown),
//                                contentDescription = null,
//                                tint = HsLinkTheme.colors.Grey400
//                            )
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        linkTypes.forEach { linkType ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = linkType,
                                        style = HsLinkTheme.typography.body_14Normal
                                    )
                                },
                                onClick = {
                                    selectedLinkType = linkType
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
        // URL
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(
                        text = "URL",
                        color = HsLinkTheme.colors.Grey700,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                    Text(
                        text = " *",
                        color = Color.Red,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                }

                HsLinkTextField(
                    value = url,
                    placeholder = "URL을 입력해주세요",
                    onValueChanged = { url = it },
                    borderColor = if (isUrlFocused) {
                        HsLinkTheme.colors.DeepBlue500
                    } else {
                        HsLinkTheme.colors.Grey200
                    },
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { isUrlFocused = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item {
            HsLinkActionButton(
                label = "수정완료",
                onClick = onSaveClick,
                size = HsLinkActionButtonSize.Large,
                isEnabled = isFormValid,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
    // Dialog 표시
    if (showExitDialog) {
        UnsavedChangesDialog(
            onDismiss = { showExitDialog = false },
            onConfirm = {
                showExitDialog = false
                onBackClick() // 실제 나가기
            }
        )
    }
}

@Composable
private fun LinkTypeDropdown(
    query: String,
    onLinkTypeSelect: (String) -> Unit
) {
    val linkTypes = listOf("기타", "링크드인", "인스타그램", "깃허브", "노션", "구글 드라이브")
    val filteredTypes = linkTypes.filter {
        it.contains(query, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, HsLinkTheme.colors.Grey200, RoundedCornerShape(8.dp))
            .background(HsLinkTheme.colors.Common)
    ) {
        LazyColumn {
            items(filteredTypes.size) { index ->
                val linkType = filteredTypes[index]

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLinkTypeSelect(linkType) }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = linkType,
                        style = HsLinkTheme.typography.body_14Normal,
                        color = HsLinkTheme.colors.Grey700
                    )
                }

                if (index < filteredTypes.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 1.dp,
                        color = HsLinkTheme.colors.Grey200
                    )
                }
            }
        }
    }
}