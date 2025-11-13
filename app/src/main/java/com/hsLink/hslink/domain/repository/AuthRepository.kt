package com.hsLink.hslink.domain.repository

import com.hsLink.hslink.data.dto.response.auth.SocialLoginResponseDto
import com.hsLink.hslink.data.dto.response.auth.WithdrawResponseDto

interface AuthRepository {
    suspend fun loginWithSocialToken(
        provider: String,
        accessToken: String
    ): Result<SocialLoginResponseDto>

    suspend fun logout(): Result<Unit>
    suspend fun withdraw(): Result<WithdrawResponseDto>
}