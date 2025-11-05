package com.hsLink.hslink.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButton
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButtonSize
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.onboarding.component.OnboardingProgressBar
import com.hsLink.hslink.presentation.onboarding.component.screen.CareerScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.EmploymentStatusScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.JobInfoScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.JobSeekingScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.LinksScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.MajorScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.MentorshipScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.NameScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.StudentIdScreen
import com.hsLink.hslink.presentation.onboarding.model.OnboardingStep
import com.hsLink.hslink.presentation.onboarding.viewmodel.OnboardingViewModel

@Composable
fun OnboardingRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state.currentStep) {
        OnboardingStep.STUDENT_ID -> {
            StudentIdScreen(
                studentId = state.studentId,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onStudentIdChange = viewModel::updateStudentId,
                onPreviousClick = navigateUp,
                onNextClick = viewModel::moveToNextStep
            )
        }

        OnboardingStep.NAME -> {
            NameScreen(
                name = state.name,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onNameChange = viewModel::updateName,
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = viewModel::moveToNextStep
            )
        }

        OnboardingStep.MAJOR -> {
            MajorScreen(
                major = state.major,
                majorQuery = state.majorQuery,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onMajorQueryChange = viewModel::updateMajorQuery,
                onMajorSelect = viewModel::updateMajor,
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = viewModel::moveToNextStep
            )
        }

        OnboardingStep.EMPLOYMENT_STATUS -> {
            EmploymentStatusScreen(
                selectedStatus = state.employmentStatus,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onStatusSelect = viewModel::updateEmploymentStatus,
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = viewModel::moveToNextStep
            )
        }

        OnboardingStep.CAREER -> {
            CareerScreen(
                selectedStatus = state.employmentStatus,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onStatusSelect = {},
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = viewModel::moveToNextStep
            )
        }

        OnboardingStep.JOB_INFO -> {
            JobInfoScreen(
                startDate = state.startDate,
                endDate = state.endDate,
                isCurrentlyEmployed = state.isCurrentlyEmployed,
                companyName = state.companyName,
                jobName = state.jobName,
                selectedJobType = state.jobType,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onStartDateChange = viewModel::updateStartDate,
                onEndDateChange = viewModel::updateEndDate,
                onCurrentlyEmployedChange = viewModel::updateIsCurrentlyEmployed,
                onCompanyNameChange = viewModel::updateCompanyName,
                onJobNameChange = viewModel::updateJobName,
                onJobTypeSelect = viewModel::updateJobType,
                onCancelClick = viewModel::moveToPreviousStep,
                onSaveClick = viewModel::moveToNextStep
            )
        }

        OnboardingStep.JOB_SEEKING -> {
            JobSeekingScreen(
                isJobSeeking = state.isJobSeeking,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onJobSeekingSelect = viewModel::updateJobSeeking,
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = viewModel::moveToNextStep
            )
        }

        OnboardingStep.MENTORSHIP -> {
            MentorshipScreen(
                wantsMentorship = state.wantsMentorship,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onMentorshipSelect = viewModel::updateMentorship,
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = viewModel::moveToNextStep
            )
        }

        OnboardingStep.LINKS -> {
            LinksScreen(
                links = state.links,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onAddLink = viewModel::addLink,
                onRemoveLink = viewModel::removeLink,
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = {
                    viewModel.submitOnboarding()
                    navigateToHome()
                }
            )
        }
    }
}

@Composable
fun OnboardingScreen(
    title: AnnotatedString,
    progress: Float,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    example: String? = null,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    showPreviousButton: Boolean = true,
    nextButtonEnabled: Boolean = false,
    nextButtonLabel: String = "다음",
    onPreviousClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(HsLinkTheme.colors.Common)
            .padding(paddingValues)
    ) {
        OnboardingProgressBar(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = title,
                    style = HsLinkTheme.typography.title_24Strong,
                    color = HsLinkTheme.colors.Grey700
                )

                subtitle?.let {
                    Text(
                        text = it,
                        style = HsLinkTheme.typography.title_14Strong,
                        color = HsLinkTheme.colors.Grey300
                    )
                }

                example?.let {
                    Text(
                        text = "예시: $it",
                        style = HsLinkTheme.typography.title_14Strong,
                        color = HsLinkTheme.colors.Grey300
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                content()
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (showPreviousButton) {
                    HsLinkActionButton(
                        label = "이전",
                        onClick = onPreviousClick,
                        size = HsLinkActionButtonSize.Small,
                        modifier = Modifier.weight(1f),
                        isEnabled = true,
                    )
                }

                HsLinkActionButton(
                    label = nextButtonLabel,
                    onClick = onNextClick,
                    size = HsLinkActionButtonSize.Large,
                    modifier = Modifier.weight(if (showPreviousButton) 1f else 1f),
                    isEnabled = nextButtonEnabled,
                )
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    title: String,
    progress: Float,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    example: String? = null,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    showPreviousButton: Boolean = true,
    nextButtonEnabled: Boolean = false,
    nextButtonLabel: String = "다음",
    onPreviousClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    OnboardingScreen(
        title = AnnotatedString(title),
        progress = progress,
        modifier = modifier,
        subtitle = subtitle,
        example = example,
        paddingValues = paddingValues,
        showPreviousButton = showPreviousButton,
        nextButtonEnabled = nextButtonEnabled,
        nextButtonLabel = nextButtonLabel,
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick,
        content = content
    )
}
