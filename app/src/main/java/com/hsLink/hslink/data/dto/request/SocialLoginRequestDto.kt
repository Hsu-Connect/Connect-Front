package com.hsLink.hslink.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class SocialLoginRequestDto(
    val provider: String,
    val accessToken: String
)