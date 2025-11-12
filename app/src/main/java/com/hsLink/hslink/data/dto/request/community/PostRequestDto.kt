package com.hsLink.hslink.data.dto.request.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostRequestDto (
    @SerialName("postType")
    val postType: String,
    @SerialName("title")
    val title : String,
    @SerialName("body")
    val body : String
)