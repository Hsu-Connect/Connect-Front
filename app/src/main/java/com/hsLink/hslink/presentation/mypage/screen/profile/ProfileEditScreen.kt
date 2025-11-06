package com.hsLink.hslink.presentation.mypage.screen.profile

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButton
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.mypage.component.profile.CareerCard
import com.hsLink.hslink.presentation.mypage.component.profile.SNSCard

enum class MajorType(val displayName: String) {
    ACCOUNTING("회계재무경영"),
    CS("컴퓨터공학"),
    BUSINESS("경영학"),
    NEW_MEDIA("뉴미디어 광고 커뮤니케이션디자인"),
    UIUX("UI/UX 디자인"),
    INDUSTRIAL("산업디자인"),
    VIDEO("영상디자인"),
    BRAND("브랜드 디자인"),
}

@Preview(showBackground = true)
@Composable
private fun ProfileEditScreenPreview() {
    HsLinkTheme {
        ProfileEditScreen(
            paddingValues = PaddingValues(),
            onBackClick = { },
            onCloseClick = { }
        )
    }
}

@Composable
fun ProfileEditScreenRoute(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    ProfileEditScreen(
        paddingValues = paddingValues,
        onBackClick = onBackClick,
        onCloseClick = onCloseClick
    )
}

@Composable
fun ProfileEditScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit = {},
) {
    var studentId by remember { mutableStateOf("") }
    var isStudentIdFocused by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var isNameFocused by remember { mutableStateOf(false) }

    var selectedMajor by remember { mutableStateOf("") }
    var majorQuery by remember { mutableStateOf("") }
    var isMajorFocused by remember { mutableStateOf(false) }

    var selectedMentorType by remember { mutableStateOf("") }

    var selectedJobStatus by remember { mutableStateOf("") }

    fun isFormValid(): Boolean {
        return studentId.isNotEmpty() &&
                name.isNotEmpty() &&
                selectedMajor.isNotEmpty() &&
                selectedMentorType.isNotEmpty() &&
                selectedJobStatus.isNotEmpty()
    }
    
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
                        text = "프로필 수정하기",
                        style = HsLinkTheme.typography.title_20Strong
                    )
                },
                leftIcon = R.drawable.ic_topbar_arrowleft,
                rightIconFirst = R.drawable.ic_topbar_close,
                onLeftIconClick = onBackClick,
                onRightIconFirstClick = onCloseClick
            )
        }

        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(
                        text = "학번",
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
                    value = studentId,
                    placeholder = "21311114",
                    onValueChanged = { studentId = it },
                    borderColor = if (isStudentIdFocused) {
                        HsLinkTheme.colors.DeepBlue500
                    } else {
                        HsLinkTheme.colors.Grey200
                    },
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { isStudentIdFocused = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(
                        text = "이름",
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
                    value = name,
                    placeholder = "이리라",
                    onValueChanged = { name = it },
                    borderColor = if (isNameFocused) {
                        HsLinkTheme.colors.DeepBlue500
                    } else {
                        HsLinkTheme.colors.Grey200
                    },
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { isNameFocused = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(
                        text = "전공선택",
                        color = HsLinkTheme.colors.Grey700,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                    Text(
                        text = " *",
                        color = Color.Red,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                }

                Box {
                    HsLinkTextField(
                        value = if (selectedMajor.isNotEmpty()) selectedMajor else majorQuery,
                        placeholder = "전공을 선택해주세요",
                        onValueChanged = { newValue ->
                            if (selectedMajor.isNotEmpty()) {
                                selectedMajor = ""
                            }
                            majorQuery = newValue
                        },
                        borderColor = if (isMajorFocused) {
                            HsLinkTheme.colors.DeepBlue500
                        } else {
                            HsLinkTheme.colors.Grey200
                        },
                        backgroundColor = HsLinkTheme.colors.Common,
                        onFocusChanged = { isMajorFocused = it },
                        leadingIconRes = if (selectedMajor.isEmpty()) {
                            R.drawable.ic_profile_search
                        } else {
                            null
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // X 버튼
                    if (selectedMajor.isNotEmpty()) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_topbar_close),
                            contentDescription = "지우기",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 12.dp)
                                .clickable {
                                    selectedMajor = ""
                                    majorQuery = ""
                                }
                        )
                    }
                }

                // 드롭다운
                if (majorQuery.isNotEmpty() && selectedMajor.isEmpty() && isMajorFocused) {
                    MajorDropdown(
                        query = majorQuery,
                        onMajorSelect = { selected ->
                            selectedMajor = selected
                            majorQuery = selected
                            isMajorFocused = false
                        }
                    )
                }
            }
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(
                        text = "커리어 공유",
                        color = HsLinkTheme.colors.Grey700,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                }

                Box {
                    CareerCard(
                        name = "투썸플레이스",
                        title = "영업",
                        dateRange = "2024.02 ~ 2024.10",  // ← subtitle을 dateRange로 변경
                        onClick = { }
                    )
                }
            }
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(
                        text = "멘토 참여 여부",
                        color = HsLinkTheme.colors.Grey700,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                    Text(
                        text = " *",
                        color = Color.Red,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HsLinkSelectButton(
                        label = "멘토로도 참여할래요",
                        onClick = {
                            selectedMentorType = "mentor"
                        },
                        size = HsLinkButtonSize.Large,
                        isSelected = selectedMentorType == "",
                        modifier = Modifier.fillMaxWidth()
                    )

                    HsLinkSelectButton(
                        label = "멘티로만 참여할래요",
                        onClick = {
                            selectedMentorType = "mentee"
                        },
                        size = HsLinkButtonSize.Large,
                        isSelected = selectedMentorType == "mentee",  // ← 선택 상태
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(
                        text = "SNS",
                        color = HsLinkTheme.colors.Grey700,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                }

                SNSCard(
                    title = "SNS",
                    content = "인스타그램",
                    onClick = {
                        // SNS 설정 화면으로 이동
                    }
                )
            }
        }

        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(
                        text = "구직 여부",
                        color = HsLinkTheme.colors.Grey700,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                    Text(
                        text = " *",
                        color = Color.Red,
                        style = HsLinkTheme.typography.title_14Strong
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HsLinkSelectButton(
                        label = "구직 중이에요",
                        onClick = {
                            selectedJobStatus = "seeking"
                        },
                        size = HsLinkButtonSize.Large,
                        isSelected = selectedJobStatus == "",
                        modifier = Modifier.fillMaxWidth()
                    )

                    HsLinkSelectButton(
                        label = "구직하지 않아요",
                        onClick = {
                            selectedJobStatus = "not_seeking"
                        },
                        size = HsLinkButtonSize.Large,
                        isSelected = selectedJobStatus == "not_seeking",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        item {
            HsLinkActionButton(
                label = "수정완료",
                onClick = {
                    // 수정 완료 로직
                    onSaveClick()
                },
                size = HsLinkActionButtonSize.Large,
                isEnabled = isFormValid(), // ← 폼 유효성 검사
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp) // ← 상단 여백
            )
        }
    }
}

@Composable
private fun MajorDropdown(
    query: String,
    onMajorSelect: (String) -> Unit
) {
    val filteredMajors = MajorType.entries.filter {
        it.displayName.contains(query, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, HsLinkTheme.colors.Grey200, RoundedCornerShape(8.dp))
            .background(HsLinkTheme.colors.Common)
    ) {
        if (filteredMajors.isEmpty()) {
            // 결과 없음
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "'$query'항목 찾을 수 없습니다",
                    color = HsLinkTheme.colors.Grey400,
                    style = HsLinkTheme.typography.body_14Normal
                )
            }

            // 직접 입력하기 (결과 없을 때)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onMajorSelect(query) }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "'$query' 직접 입력하기",
                    style = HsLinkTheme.typography.body_14Normal,
                    color = HsLinkTheme.colors.DeepBlue500
                )
            }
        } else {
            LazyColumn {
                items(filteredMajors.size) { index ->
                    val major = filteredMajors[index]

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onMajorSelect(major.displayName) }
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = major.displayName,
                            style = HsLinkTheme.typography.body_14Normal,
                            color = HsLinkTheme.colors.Grey700
                        )
                    }

                    if (index < filteredMajors.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            thickness = 1.dp,
                            color = HsLinkTheme.colors.Grey200
                        )
                    }
                }

                // 직접 입력하기 (결과 있을 때)
                item {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 1.dp,
                        color = HsLinkTheme.colors.Grey200
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onMajorSelect(query) }
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = "'$query' 직접 입력하기",
                            style = HsLinkTheme.typography.body_14Normal,
                            color = HsLinkTheme.colors.DeepBlue500
                        )
                    }
                }
            }
        }
    }
}