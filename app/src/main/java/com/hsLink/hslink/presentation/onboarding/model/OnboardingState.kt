package com.hsLink.hslink.presentation.onboarding.model

data class OnboardingState(
    val studentId: String = "",
    val name: String = "",
    val major: String = "",
    val majorQuery: String = "",
    val employmentStatus: EmploymentStatus? = null,
    val companyName: String = "",
    val jobName: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val isCurrentlyEmployed: Boolean = false,
    val jobType: JobType? = null,
    val wantsMentorship: Boolean? = null,
    val isJobSeeking: Boolean? = null,
    val links: List<ExternalLink> = emptyList(),
    val currentStep: OnboardingStep = OnboardingStep.STUDENT_ID,
)

enum class OnboardingStep(val stepNumber: Int, val totalSteps: Int = 9) {
    STUDENT_ID(1),
    NAME(2),
    MAJOR(3),
    EMPLOYMENT_STATUS(4),

    CAREER(5),
    JOB_INFO(6),
    MENTORSHIP(7),
    LINKS(8),
    JOB_SEEKING(9);


    val progress: Float
        get() = stepNumber.toFloat() / totalSteps.toFloat()
}


enum class EmploymentStatus(val label: String) {
    WORKING("재학 중"),
    GRADUATED("졸업"),
    EXPECTED_GRADUATION("졸업예정"),
    COMPLETED("수료"),
    LEAVE("휴학")
}

enum class JobType(val label: String) {
    FULL_TIME("정규직"),
    CONTRACT("계약직"),
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