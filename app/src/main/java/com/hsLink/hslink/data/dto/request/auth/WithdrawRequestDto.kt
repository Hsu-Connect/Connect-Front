// data/dto/request/auth/WithdrawRequestDto.kt
package com.hsLink.hslink.data.dto.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class WithdrawRequestDto(
    val refreshToken: String
)