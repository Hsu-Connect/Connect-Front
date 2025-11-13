package com.hsLink.hslink.data.dto.response.mypage

import kotlinx.serialization.Serializable

@Serializable
data class ProfileUpdateResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String
)