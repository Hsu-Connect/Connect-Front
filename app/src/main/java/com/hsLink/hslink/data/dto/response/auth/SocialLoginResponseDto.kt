package com.hsLink.hslink.data.dto.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class SocialLoginResponseDto(
    val accessToken: String,
    val refreshToken: String,
    val isNewUser: Boolean,
    val needsOnboarding: Boolean
)