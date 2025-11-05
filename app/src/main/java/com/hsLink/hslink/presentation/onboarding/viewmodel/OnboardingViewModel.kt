package com.hsLink.hslink.presentation.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsLink.hslink.presentation.onboarding.model.EmploymentStatus
import com.hsLink.hslink.presentation.onboarding.model.ExternalLink
import com.hsLink.hslink.presentation.onboarding.model.JobType
import com.hsLink.hslink.presentation.onboarding.model.OnboardingState
import com.hsLink.hslink.presentation.onboarding.model.OnboardingStep
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    // private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    fun updateStudentId(studentId: String) {
        _state.update { it.copy(studentId = studentId) }
    }

    fun updateName(name: String) {
        _state.update { it.copy(name = name) }
    }

    fun updateMajorQuery(query: String) {
        _state.update { it.copy(majorQuery = query) }
    }

    fun updateMajor(major: String) {
        _state.update { it.copy(major = major) }
    }

    fun updateEmploymentStatus(status: EmploymentStatus) {
        _state.update { it.copy(employmentStatus = status) }
    }

    fun updateCompanyName(companyName: String) {
        _state.update { it.copy(companyName = companyName) }
    }


    fun updateJobName(name: String) {
        _state.value = _state.value.copy(jobName = name)
    }

    fun updateJobType(jobType: JobType) {
        _state.value = _state.value.copy(jobType = jobType)
    }

    fun updateStartDate(date: String) {
        _state.value = _state.value.copy(startDate = date)
    }

    fun updateEndDate(date: String) {
        _state.value = _state.value.copy(endDate = date)
    }

    fun updateIsCurrentlyEmployed(isEmployed: Boolean) {
        _state.value = _state.value.copy(
            isCurrentlyEmployed = isEmployed,
            endDate = if (isEmployed) "" else _state.value.endDate
        )
    }

    fun updateMentorship(wantsMentorship: Boolean) {
        _state.update { it.copy(wantsMentorship = wantsMentorship) }
    }

    fun updateJobSeeking(isJobSeeking: Boolean) {
        _state.update { it.copy(isJobSeeking = isJobSeeking) }
    }

    fun addLink(link: ExternalLink) {
        _state.update { it.copy(links = it.links + link) }
    }

    fun removeLink(link: ExternalLink) {
        _state.update { it.copy(links = it.links - link) }
    }

    fun moveToNextStep() {
        _state.update {
            val nextStep = when (it.currentStep) {
                OnboardingStep.STUDENT_ID -> OnboardingStep.NAME
                OnboardingStep.NAME -> OnboardingStep.MAJOR
                OnboardingStep.MAJOR -> OnboardingStep.EMPLOYMENT_STATUS
                OnboardingStep.EMPLOYMENT_STATUS -> OnboardingStep.CAREER
                OnboardingStep.CAREER -> OnboardingStep.JOB_INFO
                OnboardingStep.JOB_INFO -> OnboardingStep.MENTORSHIP
                OnboardingStep.MENTORSHIP -> OnboardingStep.LINKS
                OnboardingStep.LINKS -> OnboardingStep.JOB_SEEKING
                OnboardingStep.JOB_SEEKING -> OnboardingStep.MENTORSHIP
            }
            it.copy(currentStep = nextStep)
        }
    }

    fun moveToPreviousStep() {
        _state.update {
            val previousStep = when (it.currentStep) {
                OnboardingStep.STUDENT_ID -> OnboardingStep.STUDENT_ID
                OnboardingStep.NAME -> OnboardingStep.STUDENT_ID
                OnboardingStep.MAJOR -> OnboardingStep.NAME
                OnboardingStep.EMPLOYMENT_STATUS -> OnboardingStep.MAJOR
                OnboardingStep.CAREER -> OnboardingStep.EMPLOYMENT_STATUS
                OnboardingStep.JOB_INFO -> OnboardingStep.CAREER
                OnboardingStep.MENTORSHIP -> OnboardingStep.JOB_INFO
                OnboardingStep.LINKS -> OnboardingStep.MENTORSHIP
                OnboardingStep.JOB_SEEKING -> OnboardingStep.LINKS
            }
            it.copy(currentStep = previousStep)
        }
    }

    fun submitOnboarding() {
        viewModelScope.launch {
            // userRepository.submitOnboarding(state.value)
        }
    }

}