package com.hsLink.hslink.data.repositoryimpl

import com.hsLink.hslink.data.dto.request.auth.LogoutRequestDto
import com.hsLink.hslink.data.dto.request.auth.SocialLoginRequestDto
import com.hsLink.hslink.data.dto.request.auth.WithdrawRequestDto
import com.hsLink.hslink.data.dto.response.auth.SocialLoginResponseDto
import com.hsLink.hslink.data.dto.response.auth.WithdrawResponseDto
import com.hsLink.hslink.data.local.TokenDataStore
import com.hsLink.hslink.data.service.login.AuthService
import com.hsLink.hslink.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
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

    // ← 새로 추가: 로그아웃
    override suspend fun logout(): Result<Unit> {
        return try {
            val refreshToken = tokenDataStore.refreshToken.first()
            if (refreshToken.isNullOrEmpty()) {
                return Result.failure(Exception("토큰이 존재하지 않습니다"))
            }

            val request = LogoutRequestDto(refreshToken)
            val response = authService.logout(request)  // <- 이제 Response<Unit>

            if (response.isSuccessful) {
                tokenDataStore.clearTokens()
                Result.success(Unit)
            } else {
                Result.failure(Exception("로그아웃에 실패했습니다: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun withdraw(): Result<WithdrawResponseDto> {
        return try {
            val refreshToken = tokenDataStore.refreshToken.first() // ← 수정
            if (refreshToken.isNullOrEmpty()) {
                return Result.failure(Exception("토큰이 존재하지 않습니다"))
            }

            val request = WithdrawRequestDto(refreshToken)
            val response = authService.withdraw(request)

            if (response.isSuccessful && response.body()?.isSuccess == true) {
                tokenDataStore.clearTokens()
                Result.success(response.body()!!.result)
            } else {
                Result.failure(Exception(response.body()?.message ?: "계정 탈퇴에 실패했습니다"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}