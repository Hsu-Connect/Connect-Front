// data/dto/response/auth/WithdrawResponseDto.kt
package com.hsLink.hslink.data.dto.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class WithdrawResponseDto(
    val userId: Long,
    val deletedAt: String
)