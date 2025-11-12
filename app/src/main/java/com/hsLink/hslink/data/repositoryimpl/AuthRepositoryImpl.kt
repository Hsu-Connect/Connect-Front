package com.hsLink.hslink.data.repositoryimpl

import com.hsLink.hslink.data.dto.request.auth.SocialLoginRequestDto
import com.hsLink.hslink.data.dto.response.auth.SocialLoginResponseDto
import com.hsLink.hslink.data.local.TokenDataStore
import com.hsLink.hslink.data.service.login.AuthService
import com.hsLink.hslink.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val tokenDataStore: TokenDataStore
) : AuthRepository {

    override suspend fun loginWithSocialToken(
        provider: String,
        accessToken: String
    ): Result<SocialLoginResponseDto> {
        return try {
            val request = SocialLoginRequestDto(provider, accessToken)
            val response = authService.socialLogin(request)

            if (response.isSuccessful) {
                response.body()?.let { loginResponse ->
                    tokenDataStore.saveTokens(loginResponse.accessToken, loginResponse.refreshToken)
                    Result.success(loginResponse)
                } ?: Result.failure(Exception("응답이 비어있습니다"))
            } else {
                Result.failure(Exception("로그인 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}