package com.hsLink.hslink.data.dto.response.community

import com.hsLink.hslink.domain.model.community.CommunityPostResponseEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityPostResponseDto (
    @SerialName("postId")
    val postId: Int,
)


fun CommunityPostResponseDto.toEntity(): CommunityPostResponseEntity {
    return CommunityPostResponseEntity(
        postId = this.postId,
    )
}