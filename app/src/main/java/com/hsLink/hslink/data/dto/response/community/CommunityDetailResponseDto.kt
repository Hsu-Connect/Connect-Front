package com.hsLink.hslink.data.dto.response.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityDetailResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("author")
    val author: String,
    @SerialName("studentId")
    val studentId: String,
    @SerialName("authorStatus")
    val authorStatus: String,
    @SerialName("title")
    val title: String,
    @SerialName("body")
    val body: String,
    @SerialName("mine")
    val mine: Boolean,
    @SerialName("comments")
    val comments: List<CommentDto>,
)

@Serializable
data class CommentDto(
    @SerialName("id")
    val id: Int,
    @SerialName("commenter")
    val commenter: String,
    @SerialName("commenterStatus")
    val commenterStatus: String,
    @SerialName("content")
    val content: String,
    @SerialName("mine")
    val mine: Boolean,
)
