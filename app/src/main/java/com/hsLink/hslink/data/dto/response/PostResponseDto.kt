package com.hsLink.hslink.data.dto.response

import com.hsLink.hslink.domain.model.PostPopularEntity
import com.hsLink.hslink.domain.model.PostPromotionEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostResponseDto(
    @SerialName("posts")
    val posts: List<PostPopular>,
)

@Serializable
data class PostPopular(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
)

@Serializable
data class PostPromotionDto(
    @SerialName("posts")
    val posts: List<PostPromotion>,
)

@Serializable
data class PostPromotion(
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


fun PostPopular.toEntity(): PostPopularEntity {
    return PostPopularEntity(
        id = this.id,
        title = this.title,
    )
}

fun PostPromotion.toEntity(): PostPromotionEntity {
    return PostPromotionEntity(
        id = this.id,
        title = this.title,
        summary = this.summary,
        author = this.author,
        studentId = this.studentId,
        authorStatus = this.authorStatus,
    )
}