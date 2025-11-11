package com.hsLink.hslink.domain.model.search

data class MentorListEntity(
    val totalMentorCount: Long,
    val page: Int,
    val size: Int,
    val totalPages: Int,
    val mentors: List<MentorEntity>
)

data class MentorEntity(
    val userId: Long,
    val name: String,
    val major: String,
    val jobSeeking: Boolean,
    val employed: Boolean,
    val academicStatus: String
)