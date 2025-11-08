package com.hsLink.hslink.presentation.mypage.screen.career

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavController
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButton
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.mypage.component.career.UnsavedChangesDialog
import com.hsLink.hslink.presentation.onboarding.model.JobType

@Preview(showBackground = true)
@Composable
private fun CareerEditScreenPreview() {
    HsLinkTheme {
        CareerEditScreen(
            paddingValues = PaddingValues(),
            onBackClick = { },
            onCloseClick = { },
            onSaveClick = { }
        )
    }
}

@Composable
fun CareerEditRoute(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    CareerEditScreen(
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
fun CareerEditScreen(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // State 관리
    var startDate by remember { mutableStateOf("2024.04") }
    var endDate by remember { mutableStateOf("2024.08") }
    var isCurrentlyEmployed by remember { mutableStateOf(false) }
    var companyName by remember { mutableStateOf("한성대학교") }
    var jobName by remember { mutableStateOf("영업직") }
    var selectedJobType by remember { mutableStateOf<JobType?>(JobType.FULL_TIME) }

    // Focus state들
    var companyFocused by remember { mutableStateOf(false) }
    var jobNameFocused by remember { mutableStateOf(false) }
    var startDateFocused by remember { mutableStateOf(false) }
    var endDateFocused by remember { mutableStateOf(false) }

    var showExitDialog by remember { mutableStateOf(false) }

    // 변경사항이 있는지 체크하는 함수 (함수 내부로 이동)
    fun hasUnsavedChanges(): Boolean {
        return startDate != "2024.04" ||
                endDate != "2024.08" ||
                companyName != "한성대학교" ||
                jobName != "영업직" ||
                selectedJobType != JobType.FULL_TIME
    }

    // 나가기 처리 함수
    fun handleExit() {
        if (hasUnsavedChanges()) {
            showExitDialog = true
        } else {
            onBackClick()
        }
    }

    val isFormValid = companyName.isNotEmpty() &&
            jobName.isNotEmpty() &&
            startDate.isNotEmpty() &&
            (endDate.isNotEmpty() || isCurrentlyEmployed) &&
            selectedJobType != null

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
                        text = "커리어 수정하기",
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
            Text(
                text = "아래의 정보를 등록해주세요",
                style = HsLinkTheme.typography.title_20Strong,
                color = HsLinkTheme.colors.Grey700
            )
        }

        // 현재 재직 여부 (날짜 범위)
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = buildAnnotatedString {
                        append("현재 재직 여부 (형식 : 24.04) ")
                        withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                            append("*")
                        }
                    },
                    style = HsLinkTheme.typography.title_14Strong,
                    color = HsLinkTheme.colors.Grey700
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HsLinkTextField(
                        value = startDate,
                        placeholder = "근무시작일",
                        onValueChanged = { startDate = it },
                        modifier = Modifier.weight(1f),
                        borderColor = if (startDateFocused) HsLinkTheme.colors.SkyBlue500 else HsLinkTheme.colors.Grey300,
                        backgroundColor = HsLinkTheme.colors.Common,
                        onFocusChanged = { startDateFocused = it },
                    )
                    Text(text = "~", style = HsLinkTheme.typography.body_16Normal)
                    HsLinkTextField(
                        value = endDate,
                        placeholder = "근무종료일",
                        onValueChanged = { endDate = it },
                        modifier = Modifier.weight(1f),
                        borderColor = if (endDateFocused) HsLinkTheme.colors.SkyBlue500 else HsLinkTheme.colors.Grey300,
                        backgroundColor = HsLinkTheme.colors.Common,
                        onFocusChanged = { endDateFocused = it },
                    )
                    HsLinkSelectButton(
                        label = "재직중",
                        onClick = { isCurrentlyEmployed = !isCurrentlyEmployed },
                        size = HsLinkButtonSize.Medium,
                        isSelected = isCurrentlyEmployed
                    )
                }
            }
        }

        // 회사명
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = buildAnnotatedString {
                        append("회사명 ")
                        withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                            append("*")
                        }
                    },
                    style = HsLinkTheme.typography.title_14Strong,
                    color = HsLinkTheme.colors.Grey700
                )
                HsLinkTextField(
                    value = companyName,
                    placeholder = "회사명을 입력해주세요",
                    onValueChanged = { companyName = it },
                    borderColor = if (companyFocused) HsLinkTheme.colors.SkyBlue500
                    else HsLinkTheme.colors.Grey300,
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { companyFocused = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // 직무명
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = buildAnnotatedString {
                        append("직무명 ")
                        withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                            append("*")
                        }
                    },
                    style = HsLinkTheme.typography.title_14Strong,
                    color = HsLinkTheme.colors.Grey700
                )
                HsLinkTextField(
                    value = jobName,
                    placeholder = "직무명을 입력해주세요",
                    onValueChanged = { jobName = it },
                    borderColor = if (jobNameFocused) HsLinkTheme.colors.SkyBlue500
                    else HsLinkTheme.colors.Grey300,
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { jobNameFocused = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

// 재직 형태 (4개 버튼)
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = buildAnnotatedString {
                        append("재직 형태 ")
                        withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                            append("*")
                        }
                    },
                    style = HsLinkTheme.typography.title_14Strong,
                    color = HsLinkTheme.colors.Grey700
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.FULL_TIME.label,
                            onClick = { selectedJobType = JobType.FULL_TIME },
                            size = HsLinkButtonSize.Large,
                            isEnabled = true,
                            isSelected = selectedJobType == JobType.FULL_TIME
                        )
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.CONTRACT.label,
                            onClick = { selectedJobType = JobType.CONTRACT },
                            size = HsLinkButtonSize.Large,
                            isEnabled = true,
                            isSelected = selectedJobType == JobType.CONTRACT
                        )
                    }

                    Row(
                        // ← 이 Row가 위 Row와 같은 레벨에 있어야 함
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.INTERN.label,
                            onClick = { selectedJobType = JobType.INTERN },
                            size = HsLinkButtonSize.Large,
                            isEnabled = true,
                            isSelected = selectedJobType == JobType.INTERN
                        )
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.FREELANCER.label,
                            onClick = { selectedJobType = JobType.FREELANCER },
                            size = HsLinkButtonSize.Large,
                            isEnabled = true,
                            isSelected = selectedJobType == JobType.FREELANCER
                        )
                    }
                }
            }
        } // ← 여기서 재직 형태 item 종료

        item {  // ← 새로운 item으로 분리
            HsLinkActionButton(
                label = "수정완료",
                onClick = onSaveClick,
                size = HsLinkActionButtonSize.Large,
                isEnabled = isFormValid,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
    if (showExitDialog) {
        UnsavedChangesDialog(
            onDismiss = { showExitDialog = false },
            onConfirm = {
                showExitDialog = false
                onBackClick()
            }
        )
    }

}
