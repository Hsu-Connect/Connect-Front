package com.hsLink.hslink.domain.repository

import com.hsLink.hslink.data.dto.response.SocialLoginResponse

interface AuthRepository {
    suspend fun loginWithSocialToken(
        provider: String,
        accessToken: String
    ): Result<SocialLoginResponse>
}