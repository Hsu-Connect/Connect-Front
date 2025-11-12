package com.hsLink.hslink.data.dto.response.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityListResponseDto(
    @SerialName("posts")
    val posts: List<CommunityListDto>,
    @SerialName("currentPage")
    val currentPage: Int,
    @SerialName("hasNext")
    val hasNext: Boolean,
    @SerialName("totalElements")
    val totalElements: Int,
)

@Serializable
data class CommunityListDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("summary")
    val summary: String,
    @SerialName("author")
    val author: String,
    @SerialName("studentId")
    val studentId: String,
    @SerialName("authorStatus")
    val authorStatus: String,
)
