package com.hsLink.hslink.presentation.onboarding.component.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen
import com.hsLink.hslink.presentation.onboarding.model.JobType

@Composable
fun JobInfoScreen(
    companyName: String,
    position: String,
    department: String,
    selectedJobType: JobType?,
    startYm: String,
    endYm: String?,
    isCurrentlyEmployed: Boolean,
    progress: Float,
    paddingValues: PaddingValues,
    onCompanyNameChange: (String) -> Unit,
    onPositionChange: (String) -> Unit,
    onDepartmentChange: (String) -> Unit,
    onJobTypeSelect: (JobType) -> Unit,
    onStartDateChange: (String) -> Unit,
    onEndDateChange: (String?) -> Unit,
    onCurrentlyEmployedChange: (Boolean) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    var companyFocused by remember { mutableStateOf(false) }
    var positionFocused by remember { mutableStateOf(false) }
    var startDateFocused by remember { mutableStateOf(false) }
    var endDateFocused by remember { mutableStateOf(false) }

    val isFormValid = companyName.isNotBlank() &&
            position.isNotBlank() &&
            selectedJobType != null &&
            startYm.matches("""^\d{4}-\d{2}$""".toRegex()) &&
            (isCurrentlyEmployed || (endYm != null && endYm.matches("""^\d{4}-\d{2}$""".toRegex())))

    OnboardingScreen(
        title = buildAnnotatedString {
            append("재직 정보를 등록해주세요 ")
            withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                append("*")
            }
        },
        progress = progress,
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = isFormValid,
        nextButtonLabel = "저장하기",
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = buildAnnotatedString {
                        append("재직 기간 ")
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
                    YearMonthTextField(
                        value = startYm,
                        placeholder = "YYYY-MM",
                        modifier = Modifier.weight(1f),
                        onValueChanged = onStartDateChange,
                        isFocused = startDateFocused,
                        onFocusChanged = { startDateFocused = it }
                    )

                    Text(text = "~", style = HsLinkTheme.typography.body_16Normal)

                    YearMonthTextField(
                        value = endYm ?: "",
                        placeholder = "YYYY-MM",
                        modifier = Modifier.weight(1f),
                        onValueChanged = onEndDateChange,
                        isFocused = endDateFocused,
                        onFocusChanged = { endDateFocused = it },
                        enabled = !isCurrentlyEmployed
                    )

                    HsLinkSelectButton(
                        label = "재직중",
                        onClick = {
                            onCurrentlyEmployedChange(!isCurrentlyEmployed)
                            if (!isCurrentlyEmployed) {
                                onEndDateChange(null)
                            }
                        },
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
                    value = position,
                    placeholder = "직무명을 입력해주세요 (예: Android 개발자)",
                    onValueChanged = onPositionChange,
                    borderColor = if (positionFocused) HsLinkTheme.colors.SkyBlue500
                    else HsLinkTheme.colors.Grey300,
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { positionFocused = it },
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
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.PERMANENT.label,
                            onClick = { onJobTypeSelect(JobType.PERMANENT) },
                            size = HsLinkButtonSize.Large,
                            isSelected = selectedJobType == JobType.PERMANENT
                        )
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.TEMPORARY.label,
                            onClick = { onJobTypeSelect(JobType.TEMPORARY) },
                            size = HsLinkButtonSize.Large,
                            isSelected = selectedJobType == JobType.TEMPORARY
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.INTERN.label,
                            onClick = { onJobTypeSelect(JobType.INTERN) },
                            size = HsLinkButtonSize.Large,
                            isSelected = selectedJobType == JobType.INTERN
                        )
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = JobType.FREELANCER.label,
                            onClick = { onJobTypeSelect(JobType.FREELANCER) },
                            size = HsLinkButtonSize.Large,
                            isSelected = selectedJobType == JobType.FREELANCER
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun YearMonthTextField(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    isFocused: Boolean,
    onFocusChanged: (Boolean) -> Unit,
    enabled: Boolean = true
) {
    HsLinkTextField(
        value = value,
        placeholder = placeholder,
        modifier = modifier,
        onValueChanged = { newValue ->
            val filtered = newValue.filter { it.isDigit() || it == '-' }
            val formatted = when {
                filtered.length <= 4 -> filtered
                filtered.length == 5 && !filtered.contains("-") ->
                    "${filtered.substring(0, 4)}-${filtered.substring(4)}"
                filtered.length > 7 -> filtered.take(7)
                else -> filtered
            }
            onValueChanged(formatted)
        },
        borderColor = if (isFocused) HsLinkTheme.colors.SkyBlue500
        else HsLinkTheme.colors.Grey300,
        backgroundColor = if (enabled) HsLinkTheme.colors.Common
        else HsLinkTheme.colors.Grey100,
        onFocusChanged = onFocusChanged,
    )
}

@Preview(showBackground = true)
@Composable
private fun JobInfoScreenPreview() {
    HsLinkTheme {
        JobInfoScreen(
            companyName = "에이치스 링크",
            position = "Android 개발자",
            department = "개발팀",
            selectedJobType = JobType.PERMANENT,
            startYm = "2024-01",
            endYm = null,
            isCurrentlyEmployed = true,
            progress = 0.5f,
            paddingValues = PaddingValues(),
            onCompanyNameChange = {},
            onPositionChange = {},
            onDepartmentChange = {},
            onJobTypeSelect = {},
            onStartDateChange = {},
            onEndDateChange = {},
            onCurrentlyEmployedChange = {},
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}