package com.hsLink.hslink.data.dto.response.mypage

import kotlinx.serialization.Serializable

@Serializable
data class MyPageUserProfileDto(
    val userId: Long,
    val studentNumber: String,
    val name: String,
    val major: String,
    val mentor: Boolean,
    val jobSeeking: Boolean,
    val academicStatus: String,
    val careers: List<CareerItemDto>,
    val links: List<LinkItemDto>
)