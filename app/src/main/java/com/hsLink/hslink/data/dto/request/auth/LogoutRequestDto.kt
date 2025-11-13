// data/dto/request/auth/LogoutRequestDto.kt
package com.hsLink.hslink.data.dto.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequestDto(
    val refreshToken: String
)