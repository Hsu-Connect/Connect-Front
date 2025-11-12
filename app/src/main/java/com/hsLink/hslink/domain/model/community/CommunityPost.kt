package com.hsLink.hslink.domain.model.community

import com.hsLink.hslink.data.dto.response.community.CommunityListDto

data class CommunityPost(
    val id: Int,
    val title: String,
    val summary: String,
    val author: String,
    val studentId: String,
    val authorStatus: String,
)

fun CommunityListDto.toEntity(): CommunityPost {
    return CommunityPost(
        id = id,
        title = title,
        summary = summary,
        author = author,
        studentId = studentId,
        authorStatus = authorStatus,
    )
}