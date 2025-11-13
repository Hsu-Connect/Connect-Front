package com.hsLink.hslink.presentation.onboarding.model

import com.hsLink.hslink.domain.model.search.CareerItemEntity
import com.hsLink.hslink.domain.model.search.LinkItemEntity

data class OnboardingState(
    val studentId: String = "",
    val name: String = "",
    val major: String = "",
    val majorQuery: String = "",
    val academicStatus: AcademicStatus? = null,
    val career: Boolean? = null,
    val isExperiencedPath: Boolean = false,
    val companyName: String = "",
    val position: String = "",
    val department: String = "",
    val jobType: JobType? = null,
    val careerList: List<CareerItemEntity> = emptyList(),
    val linkList: List<LinkItemEntity> = emptyList(),
    val tempCompanyName: String = "",
    val tempPosition: String = "",
    val tempDepartment: String = "",
    val tempJobType: JobType? = null,
    val tempStartYm: String = "",
    val tempEndYm: String? = null,
    val tempIsEmployed: Boolean = false,
    val tempLinkType: LinkType? = null,
    val tempLinkUrl: String = "",
    val wantsMentorship: Boolean? = null,
    val isJobSeeking: Boolean? = null,
    val currentStep: OnboardingStep = OnboardingStep.STUDENT_ID,
    val email: String = "",
    val isLoading: Boolean = false,
    val apiError: String? = null
)


enum class OnboardingStep(val stepNumber: Int, val totalSteps: Int = 11) {
    STUDENT_ID(1),
    NAME(2),
    MAJOR(3),
    EMPLOYMENT_STATUS(4),
    CAREER(5),
    JOB_INFO(6),
    JOB_SEEKING(7),
    MENTORSHIP(8),
    EMAIL(9),
    LINKS_LIST(10),
    LINKS(11);


    val progress: Float
        get() = stepNumber.toFloat() / totalSteps.toFloat()

    fun next(): OnboardingStep? {
        val nextStepNumber = this.stepNumber + 1
        return entries.find { it.stepNumber == nextStepNumber }
    }

    fun previous(): OnboardingStep? {
        val previousStepNumber = this.stepNumber - 1
        return entries.find { it.stepNumber == previousStepNumber }
    }
}


enum class AcademicStatus(val label: String) {
    ENROLLED("재학 중"),     // WORKING → ENROLLED (API 스펙에 맞게)
    GRADUATED("졸업"),
    EXPECTED_GRADUATION("졸업예정"),
    COMPLETED("수료"),
    LEAVE("휴학")
}

enum class JobType(val label: String) {
    PERMANENT("정규직"),
    TEMPORARY("계약직"),
    INTERN("인턴"),
    FREELANCER("프리랜서")
}

data class ExternalLink(
    val type: LinkType,
    val url: String,
)

enum class LinkType(val label: String) {
    GITHUB("깃허브"),
    BLOG("블로그"),
    PORTFOLIO("포트폴리오"),
    LINKEDIN("링크드인"),
    OTHER("기타")
}