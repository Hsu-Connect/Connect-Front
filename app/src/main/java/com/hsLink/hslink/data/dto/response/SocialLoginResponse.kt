package com.hsLink.hslink.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class SocialLoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val isNewUser: Boolean,
    val needsOnboarding: Boolean
)