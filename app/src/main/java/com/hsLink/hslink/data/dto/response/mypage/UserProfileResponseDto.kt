package com.hsLink.hslink.data.dto.response.mypage

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponseDto(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: UserProfileDto
)
@Serializable
data class UserProfileDto(
    val userId: Long,
    val name: String,
    val studentNumberPrefix: String,
    val major: String,
    val email: String,
    val jobSeeking: Boolean,
    val employed: Boolean,
    val academicStatus: String,
    val careers: List<CareerItemDto>,
    val links: List<LinkItemDto>
)
@Serializable
data class CareerItemDto(
    val id: Long,
    val companyName: String,
    val position: String,
    val jobType: String,
    val employed: Boolean,
    val startYm: String,
    val endYm: String?
)
@Serializable
data class LinkItemDto(
    val id: Long,
    val type: String,
    val url: String
)