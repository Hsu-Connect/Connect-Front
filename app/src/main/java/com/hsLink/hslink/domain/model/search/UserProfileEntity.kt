package com.hsLink.hslink.domain.model.search

import com.hsLink.hslink.presentation.onboarding.model.JobType
import com.hsLink.hslink.presentation.onboarding.model.LinkType

data class UserProfileEntity(
    val userId: Long,
    val name: String,
    val studentNumberPrefix: String,
    val major: String,
    val email: String,
    val jobSeeking: Boolean,
    val employed: Boolean,
    val academicStatus: String,
    val careers: List<CareerEntity>,
    val links: List<LinkEntity>
)

data class CareerItemEntity(
    val id: Int,
    val companyName: String,
    val position: String,
    val jobType: JobType,
    val employed: Boolean,
    val startYm: String,
    val endYm: String?
)

data class CareerEntity(
    val id: Long,
    val companyName: String,
    val position: String,
    val jobType: String,
    val employed: Boolean,
    val startYm: String,
    val endYm: String?
)

data class LinkEntity(
    val id: Long,
    val type: String,
    val url: String
)

data class LinkItemEntity(
    val id: Long,
    val type: LinkType,
    val url: String
)