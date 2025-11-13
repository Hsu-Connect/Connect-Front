package com.hsLink.hslink.presentation.onboarding

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButton
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButtonSize
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.data.dto.response.onboarding.CareerDto
import com.hsLink.hslink.presentation.mypage.navigation.career.navigateToCareerEdit
import com.hsLink.hslink.presentation.mypage.viewmodel.CareerViewModel
import com.hsLink.hslink.presentation.onboarding.component.OnboardingProgressBar
import com.hsLink.hslink.presentation.onboarding.component.screen.CareerScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.EmailScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.EmploymentStatusScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.JobInfoScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.JobSeekingScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.LinksScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.MajorScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.MentorshipScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.NameScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.StudentIdScreen
import com.hsLink.hslink.presentation.onboarding.component.screen.LinkAddScreen // 새로운 폼 임포트
import com.hsLink.hslink.presentation.onboarding.model.OnboardingStep
import com.hsLink.hslink.presentation.onboarding.viewmodel.OnboardingViewModel

@Composable
fun OnboardingRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    navController: NavController,
    viewModel: OnboardingViewModel = hiltViewModel(),
    //careerViewModel: CareerViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    //val careerList by careerViewModel.careers.collectAsStateWithLifecycle()


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
                selectedStatus = state.academicStatus, // ← employmentStatus → academicStatus
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onStatusSelect = viewModel::updateAcademicStatus, // ← updateEmploymentStatus → updateAcademicStatus
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = viewModel::moveToNextStep
            )
        }

        OnboardingStep.CAREER -> {
            // ← 안전한 변환으로 수정
            val safeCareerList = try {
                state.careerList.map { career ->
                    CareerDto(
                        id = career.id,
                        companyName = career.companyName,
                        position = career.position,
                        jobType = career.jobType,
                        employed = career.employed,
                        startYm = career.startYm,
                        endYm = career.endYm
                    )
                }
            } catch (e: Exception) {
                Log.e("OnboardingRoute", "타입 변환 실패", e)
                emptyList()
            }

            CareerScreen(
                selectedCareer = state.career,
                careerList = safeCareerList, // ← 안전한 리스트 사용
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onCareerSelect = viewModel::updateCareer,
                onCareerClick = { career -> // career는 이제 CareerDto 타입
                    navController.navigateToCareerEdit(careerId = career.id) // ← .toLong() 제거 (이미 Int로 맞춤)
                },
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = viewModel::moveToNextStep,
                onAddCareerClick = viewModel::openJobInfoForm
            )
        }

        OnboardingStep.JOB_INFO -> {
            JobInfoScreen(
                companyName = state.tempCompanyName,
                position = state.tempPosition,
                department = state.tempDepartment,
                selectedJobType = state.tempJobType,
                startYm = state.tempStartYm,
                endYm = state.tempEndYm,
                isCurrentlyEmployed = state.tempIsEmployed,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onCompanyNameChange = viewModel::updateTempCompanyName,
                onPositionChange = viewModel::updateTempPosition,
                onDepartmentChange = viewModel::updateTempDepartment,
                onJobTypeSelect = viewModel::updateTempJobType,
                onStartDateChange = viewModel::updateTempStartYm,
                onEndDateChange = viewModel::updateTempEndYm,
                onCurrentlyEmployedChange = viewModel::updateTempIsEmployed,
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = viewModel::submitJobInfoAndReturnToCareerList
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

        OnboardingStep.EMAIL -> {
            EmailScreen(
                email = state.email,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onEmailChange = viewModel::updateEmail,
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = viewModel::moveToNextStep
            )
        }

        OnboardingStep.LINKS_LIST -> {
            LinksScreen(
                linkList = state.linkList,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = {
                    viewModel.submitOnboarding()
                    navigateToHome()
                },
                onAddLinkClick = viewModel::openLinkForm
            )
        }

        OnboardingStep.LINKS -> {
            LinkAddScreen(
                type = state.tempLinkType,
                url = state.tempLinkUrl,
                progress = state.currentStep.progress,
                paddingValues = paddingValues,
                onTypeSelect = viewModel::updateTempLinkType,
                onUrlChange = viewModel::updateTempLinkUrl,
                onPreviousClick = viewModel::moveToPreviousStep,
                onNextClick = viewModel::submitLinkAndReturnToLinkList
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

            // Content
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