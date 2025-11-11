package com.hsLink.hslink.data.repositoryimpl

import com.hsLink.hslink.data.dto.request.SocialLoginRequest
import com.hsLink.hslink.data.dto.response.SocialLoginResponse
import com.hsLink.hslink.data.service.login.AuthService
import com.hsLink.hslink.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun loginWithSocialToken(
        provider: String,
        accessToken: String
    ): Result<SocialLoginResponse> {
        return try {
            val request = SocialLoginRequest(provider, accessToken)
            val response = authService.socialLogin(request)

            if (response.isSuccessful) {
                response.body()?.let { loginResponse ->
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