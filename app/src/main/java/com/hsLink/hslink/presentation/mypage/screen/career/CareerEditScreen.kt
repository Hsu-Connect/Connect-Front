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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButton
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.data.dto.request.onboarding.CareerUpdateRequestDto
import com.hsLink.hslink.data.dto.response.onboarding.CareerDto
import com.hsLink.hslink.presentation.mypage.component.career.UnsavedChangesDialog
import com.hsLink.hslink.presentation.mypage.viewmodel.CareerViewModel
import com.hsLink.hslink.presentation.onboarding.model.JobType

@Preview(showBackground = true)
@Composable
private fun CareerEditScreenPreview() {
    HsLinkTheme {
//        CareerEditScreen(
//            paddingValues = PaddingValues(),
//            onBackClick = { },
//            onCloseClick = { },
//            onSaveClick = { }
//        )
    }
}

@Composable
fun CareerEditRoute(
    paddingValues: PaddingValues,
    navController: NavController,
    careerId: Long?, // ← Long?
    careerViewModel: CareerViewModel = hiltViewModel()
) {
    val selectedCareer by careerViewModel.selectedCareer.collectAsState()
    val isLoading by careerViewModel.isLoading.collectAsState()

    LaunchedEffect(careerId) {
        careerId?.let { id ->
            careerViewModel.loadCareer(id)
        }
    }

    CareerEditScreen(
        paddingValues = paddingValues,
        career = selectedCareer,
        isLoading = isLoading,
        onBackClick = { navController.popBackStack() },
        onCloseClick = { navController.popBackStack() },
        onSaveClick = { companyName, position, jobType, startYm, endYm, employed ->
            careerId?.let { id -> // ← null 체크
                val requestDto = CareerUpdateRequestDto( // ← 실제 DTO 생성
                    companyName = companyName,
                    position = position,
                    jobType = jobType,
                    startYm = startYm,
                    endYm = endYm,
                    employed = employed
                )
                careerViewModel.updateCareer(id, requestDto) // ← 실제 API 호출
            }
            navController.popBackStack()
        }
    )
}

@Composable
fun CareerEditScreen(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    onSaveClick: (String, String, JobType, String, String?, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    career: CareerDto? = null, // ← 추가
    isLoading: Boolean = false, // ← 추가
) {
    // 기존 하드코딩된 초기값들을 career 데이터로 교체
    var startDate by remember(career) { mutableStateOf(career?.startYm ?: "") }
    var endDate by remember(career) { mutableStateOf(career?.endYm ?: "") }
    var isCurrentlyEmployed by remember(career) { mutableStateOf(career?.employed ?: false) }
    var companyName by remember(career) { mutableStateOf(career?.companyName ?: "") }
    var jobName by remember(career) { mutableStateOf(career?.position ?: "") }
    var selectedJobType by remember(career) { mutableStateOf(career?.jobType) }

    // ← 누락된 Focus 상태 변수들 추가
    var companyFocused by remember { mutableStateOf(false) }
    var jobNameFocused by remember { mutableStateOf(false) }
    var startDateFocused by remember { mutableStateOf(false) }
    var endDateFocused by remember { mutableStateOf(false) }

    // ← 누락된 Dialog 상태 변수 추가
    var showExitDialog by remember { mutableStateOf(false) }

    // ← 수정된 hasUnsavedChanges 함수
    fun hasUnsavedChanges(): Boolean {
        return startDate != (career?.startYm ?: "") ||
                endDate != (career?.endYm ?: "") ||
                companyName != (career?.companyName ?: "") ||
                jobName != (career?.position ?: "") ||
                selectedJobType != career?.jobType ||
                isCurrentlyEmployed != (career?.employed ?: false)
    }

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
                onLeftIconClick = { handleExit() },
                onRightIconFirstClick = { handleExit() }
            )
        }

        item {
            Text(
                text = "아래의 정보를 수정해주세요", // ← 문구 수정
                style = HsLinkTheme.typography.title_20Strong,
                color = HsLinkTheme.colors.Grey700
            )
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = buildAnnotatedString {
                        append("재직 기간 (형식 : 24.04) ")
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
                        onValueChanged = {
                            if (!isCurrentlyEmployed) { // ← 재직중이 아닐 때만 변경 허용
                                endDate = it
                            }
                        },
                        modifier = Modifier.weight(1f),
                        borderColor = if (endDateFocused) HsLinkTheme.colors.SkyBlue500 else HsLinkTheme.colors.Grey300,
                        backgroundColor = HsLinkTheme.colors.Common,
                        onFocusChanged = { endDateFocused = it },
                    )
                    HsLinkSelectButton(
                        label = "재직중",
                        onClick = {
                            isCurrentlyEmployed = !isCurrentlyEmployed
                            if (isCurrentlyEmployed) endDate = "" // 재직중이면 종료일 초기화
                        },
                        size = HsLinkButtonSize.Medium,
                        isSelected = isCurrentlyEmployed
                    )
                }
            }
        }

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
                            label = JobType.PERMANENT.label,
                            onClick = { selectedJobType = JobType.PERMANENT },
                            size = HsLinkButtonSize.Large,
                            isEnabled = true,
                            isSelected = selectedJobType == JobType.PERMANENT
                        )
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.TEMPORARY.label,
                            onClick = { selectedJobType = JobType.TEMPORARY },
                            size = HsLinkButtonSize.Large,
                            isEnabled = true,
                            isSelected = selectedJobType == JobType.TEMPORARY
                        )
                    }

                    Row(
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
        }

        item {
            HsLinkActionButton(
                label = "수정완료",
                onClick = {
                    // 폼 데이터 수집해서 onSaveClick에 전달
                    selectedJobType?.let { jobType ->
                        onSaveClick(
                            companyName,        // String
                            jobName,            // String
                            jobType,            // JobType
                            startDate,          // String
                            if (isCurrentlyEmployed) null else endDate, // String?
                            isCurrentlyEmployed // Boolean
                        )
                    }
                },
                size = HsLinkActionButtonSize.Large,
                isEnabled = isFormValid && !isLoading,
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
