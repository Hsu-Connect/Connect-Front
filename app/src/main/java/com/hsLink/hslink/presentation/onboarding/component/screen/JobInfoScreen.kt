package com.hsLink.hslink.presentation.onboarding.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen
import com.hsLink.hslink.presentation.onboarding.model.JobType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JobInfoScreen(
    startDate: String,
    endDate: String,
    isCurrentlyEmployed: Boolean,
    companyName: String,
    jobName: String,
    selectedJobType: JobType?,
    progress: Float,
    paddingValues: PaddingValues,
    onStartDateChange: (String) -> Unit,
    onEndDateChange: (String) -> Unit,
    onCurrentlyEmployedChange: (Boolean) -> Unit,
    onCompanyNameChange: (String) -> Unit,
    onJobNameChange: (String) -> Unit,
    onJobTypeSelect: (JobType) -> Unit,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    var companyFocused by remember { mutableStateOf(false) }
    var jobNameFocused by remember { mutableStateOf(false) }
    var startDateFocused by remember { mutableStateOf(false) }
    var endDateFocused by remember { mutableStateOf(false) }

    val isFormValid = companyName.isNotEmpty() &&
            jobName.isNotEmpty() &&
            startDate.isNotEmpty() &&
            (endDate.isNotEmpty() || isCurrentlyEmployed) &&
            selectedJobType != null

    OnboardingScreen(
        title = buildAnnotatedString {
            append("아래의 정보를 등록해주세요 ")
            withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                append("*")
            }
        },
        progress = progress,
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = isFormValid,
        nextButtonLabel = "저장하기",
        onPreviousClick = onCancelClick,
        onNextClick = onSaveClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
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
                        onValueChanged = onStartDateChange,
                        modifier = Modifier.weight(1f),
                        borderColor = if (startDateFocused) HsLinkTheme.colors.SkyBlue500 else HsLinkTheme.colors.Grey300,
                        backgroundColor = HsLinkTheme.colors.Common,
                        onFocusChanged = { startDateFocused = it },
                    )
                    Text(text = "~", style = HsLinkTheme.typography.body_16Normal)
                    HsLinkTextField(
                        value = endDate,
                        placeholder = "근무종료일",
                        onValueChanged = onEndDateChange,
                        modifier = Modifier.weight(1f),
                        borderColor = if (endDateFocused) HsLinkTheme.colors.SkyBlue500 else HsLinkTheme.colors.Grey300,
                        backgroundColor = HsLinkTheme.colors.Common,
                        onFocusChanged = { endDateFocused = it },
                    )
                    HsLinkSelectButton(
                        label = "재직중",
                        onClick = { onCurrentlyEmployedChange(!isCurrentlyEmployed) },
                        size = HsLinkButtonSize.Medium,
                        isSelected = isCurrentlyEmployed
                    )
                }
            }

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
                    onValueChanged = onCompanyNameChange,
                    borderColor = if (companyFocused) HsLinkTheme.colors.SkyBlue500
                    else HsLinkTheme.colors.Grey300,
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { companyFocused = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }

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
                    onValueChanged = onJobNameChange,
                    borderColor = if (jobNameFocused) HsLinkTheme.colors.SkyBlue500
                    else HsLinkTheme.colors.Grey300,
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { jobNameFocused = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }

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
                            onClick = { onJobTypeSelect(JobType.FULL_TIME) },
                            size = HsLinkButtonSize.Large,
                            isEnabled = true,
                            isSelected = selectedJobType == JobType.FULL_TIME
                        )
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.CONTRACT.label,
                            onClick = { onJobTypeSelect(JobType.CONTRACT) },
                            size = HsLinkButtonSize.Large,
                            isEnabled = true,
                            isSelected = selectedJobType == JobType.CONTRACT
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.INTERN.label,
                            onClick = { onJobTypeSelect(JobType.INTERN) },
                            size = HsLinkButtonSize.Large,
                            isEnabled = true,
                            isSelected = selectedJobType == JobType.INTERN
                        )
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.FREELANCER.label,
                            onClick = { onJobTypeSelect(JobType.FREELANCER) },
                            size = HsLinkButtonSize.Large,
                            isEnabled = true,
                            isSelected = selectedJobType == JobType.FREELANCER
                        )
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun JobInfoScreenPreview() {
    HsLinkTheme {
        JobInfoScreen(
            startDate = "24.01",
            endDate = "",
            isCurrentlyEmployed = true,
            companyName = "에이치스 링크",
            jobName = "Android 개발자",
            selectedJobType = JobType.FULL_TIME,
            progress = 0.5f,
            paddingValues = PaddingValues(),
            onStartDateChange = {},
            onEndDateChange = {},
            onCurrentlyEmployedChange = {},
            onCompanyNameChange = {},
            onJobNameChange = {},
            onJobTypeSelect = {},
            onCancelClick = {},
            onSaveClick = {}
        )
    }
}