package com.hsLink.hslink.presentation.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsLink.hslink.data.dto.request.onboarding.CareerRequest
import com.hsLink.hslink.data.dto.request.onboarding.LinkRequest
import com.hsLink.hslink.data.dto.request.onboarding.OnboardingRequest
import com.hsLink.hslink.domain.repository.onboarding.OnboardingRepository
import com.hsLink.hslink.presentation.onboarding.model.AcademicStatus
import com.hsLink.hslink.presentation.onboarding.model.JobType
import com.hsLink.hslink.presentation.onboarding.model.LinkType
import com.hsLink.hslink.presentation.onboarding.model.OnboardingState
import com.hsLink.hslink.presentation.onboarding.model.OnboardingStep
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            fetchCareersAndLinks()
        }
    }
    fun refreshCareersAndLinks() {
        viewModelScope.launch {
            fetchCareersAndLinks()
        }
    }


    private suspend fun fetchCareersAndLinks() {
        _state.update { it.copy(isLoading = true) }

        val careersResult = onboardingRepository.getCareers()
        val linksResult = onboardingRepository.getLinks()

        _state.update { currentState ->
            val newCareerList = careersResult.getOrNull() ?: currentState.careerList
            val newLinkList = linksResult.getOrNull() ?: currentState.linkList

            currentState.copy(
                isLoading = false,
                careerList = newCareerList,
                linkList = newLinkList,
                apiError = careersResult.exceptionOrNull()?.message
                    ?: linksResult.exceptionOrNull()?.message
            )
        }
    }


    fun moveToNextStep() {
        val currentState = _state.value
        val nextStep = when (currentState.currentStep) {
            OnboardingStep.CAREER -> {
                if (currentState.isExperiencedPath) {
                    OnboardingStep.JOB_SEEKING
                } else {
                    OnboardingStep.MENTORSHIP
                }
            }
            OnboardingStep.EMAIL -> OnboardingStep.LINKS_LIST
            OnboardingStep.LINKS_LIST -> currentState.currentStep.next()
            OnboardingStep.LINKS -> null
            else -> currentState.currentStep.next()
        }

        nextStep?.let {
            _state.update { it.copy(currentStep = nextStep) }
        }
    }

    fun moveToPreviousStep() {
        val currentState = _state.value
        val previousStep = when (currentState.currentStep) {
            OnboardingStep.MENTORSHIP -> {
                if (currentState.isExperiencedPath) OnboardingStep.JOB_SEEKING else OnboardingStep.CAREER
            }
            OnboardingStep.LINKS_LIST -> OnboardingStep.EMAIL
            OnboardingStep.LINKS -> OnboardingStep.LINKS_LIST
            OnboardingStep.JOB_INFO -> OnboardingStep.CAREER
            else -> currentState.currentStep.previous()
        }

        previousStep?.let {
            _state.update { it.copy(currentStep = previousStep) }
        }
    }

    fun openJobInfoForm() {
        _state.update {
            it.copy(
                currentStep = OnboardingStep.JOB_INFO,
                tempCompanyName = "", tempPosition = "", tempDepartment = "", tempJobType = null,
                tempStartYm = "", tempEndYm = null, tempIsEmployed = false
            )
        }
    }

    fun submitJobInfoAndReturnToCareerList() {
        viewModelScope.launch {
            val s = _state.value
            val request = CareerRequest(
                companyName = s.tempCompanyName,
                position = s.tempPosition,
                jobType = s.tempJobType ?: run {
                    return@launch
                },
                startYm = s.tempStartYm,
                endYm = s.tempEndYm,
                employed = s.tempIsEmployed,
            )

            _state.update { it.copy(isLoading = true) }

            val result = onboardingRepository.submitCareer(request)

            if (result.isSuccess) {

                fetchCareersAndLinks()

                _state.update {
                    it.copy(
                        currentStep = OnboardingStep.CAREER,
                        isLoading = false,
                        tempCompanyName = "",
                        tempPosition = "",
                        tempDepartment = "",
                        tempJobType = null,
                        tempStartYm = "",
                        tempEndYm = null,
                        tempIsEmployed = false,
                        apiError = null
                    )
                }

            } else {
                _state.update {
                    it.copy(
                        apiError = result.exceptionOrNull()?.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun openLinkForm() {

        _state.update {
            it.copy(
                currentStep = OnboardingStep.LINKS,
                tempLinkType = null,
                tempLinkUrl = ""
            )
        }
    }

    fun submitLinkAndReturnToLinkList() {
        viewModelScope.launch {
            val s = _state.value
            val request = LinkRequest(
                type = s.tempLinkType ?: run {
                    return@launch
                },
                url = s.tempLinkUrl
            )

            _state.update { it.copy(isLoading = true) }

            val result = onboardingRepository.submitLink(request)

            if (result.isSuccess) {

                fetchCareersAndLinks()

                _state.update {
                    it.copy(
                        currentStep = OnboardingStep.LINKS_LIST,
                        isLoading = false,
                        tempLinkType = null,
                        tempLinkUrl = "",
                        apiError = null
                    )
                }

            } else {
                _state.update {
                    it.copy(
                        apiError = result.exceptionOrNull()?.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun updateTempCompanyName(name: String) { _state.update { it.copy(tempCompanyName = name) } }
    fun updateTempPosition(position: String) { _state.update { it.copy(tempPosition = position) } }
    fun updateTempDepartment(department: String) { _state.update { it.copy(tempDepartment = department) } }
    fun updateTempJobType(jobType: JobType) { _state.update { it.copy(tempJobType = jobType) } }
    fun updateTempStartYm(date: String) { _state.update { it.copy(tempStartYm = date) } }
    fun updateTempEndYm(date: String?) { _state.update { it.copy(tempEndYm = date) } }
    fun updateTempIsEmployed(isEmployed: Boolean) { _state.update { it.copy(tempIsEmployed = isEmployed) } }

    fun updateTempLinkType(type: LinkType) { _state.update { it.copy(tempLinkType = type) } }
    fun updateTempLinkUrl(url: String) { _state.update { it.copy(tempLinkUrl = url) } }


    fun updateStudentId(studentId: String) { _state.update { it.copy(studentId = studentId) } }
    fun updateName(name: String) { _state.update { it.copy(name = name) } }
    fun updateMajor(major: String) { _state.update { it.copy(major = major) } }
    fun updateMajorQuery(query: String) { _state.update { it.copy(majorQuery = query) } }
    //fun updateEmploymentStatus(status: EmploymentStatus) { _state.update { it.copy(employmentStatus = status) } }
    fun updateAcademicStatus(status: AcademicStatus) {
        _state.update { it.copy(academicStatus = status) } // ← employmentStatus → academicStatus
    }
    fun updateCareer(isExperienced: Boolean) {
        _state.update { it.copy(career = isExperienced, isExperiencedPath = isExperienced) }
    }
    fun updateJobSeeking(isJobSeeking: Boolean) { _state.update { it.copy(isJobSeeking = isJobSeeking) } }
    fun updateMentorship(wantsMentorship: Boolean) { _state.update { it.copy(wantsMentorship = wantsMentorship) } }
    fun updateEmail(email: String) { _state.update { it.copy(email = email) } }

    fun submitOnboarding() {
        viewModelScope.launch {
            val currentState = _state.value
            val request = OnboardingRequest(
                name = currentState.name, major = currentState.major, studentNumber = currentState.studentId,
                jobSeeking = currentState.isJobSeeking ?: false, mentor = currentState.wantsMentorship ?: false,
                email = currentState.email,
                academicStatus = currentState.academicStatus!!
            )

            onboardingRepository.submitOnboarding(request)
                .onSuccess {
                    println("Onboarding Success")
                    // TODO: Navigate to home or show success message
                }
                .onFailure {
                    println("Onboarding Failed: ${it.message}")
                    // TODO: Show error message to user
                }
        }
    }

}