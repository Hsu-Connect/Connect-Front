package com.hsLink.hslink.data.dto.response.search

import kotlinx.serialization.Serializable

@Serializable
data class MentorListResponseDto(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: MentorListResultDto
)
@Serializable
data class MentorListResultDto(
    val totalMentorCount: Long,
    val page: Int,
    val size: Int,
    val totalPages: Int,
    val items: List<MentorItemDto>
)
@Serializable
data class MentorItemDto(
    val userId: Long,
    val name: String,
    val major: String,
    val jobSeeking: Boolean,
    val employed: Boolean,
    val academicStatus: String
)