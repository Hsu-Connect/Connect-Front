package com.hsLink.hslink.data.service.login

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.request.auth.LogoutRequestDto
import com.hsLink.hslink.data.dto.request.auth.SocialLoginRequestDto
import com.hsLink.hslink.data.dto.request.auth.WithdrawRequestDto
import com.hsLink.hslink.data.dto.response.auth.SocialLoginResponseDto
import com.hsLink.hslink.data.dto.response.auth.WithdrawResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun socialLogin(@Body request: SocialLoginRequestDto): Response<SocialLoginResponseDto>

    @POST("auth/logout")
    suspend fun logout(@Body request: LogoutRequestDto): Response<Unit>

    @DELETE("auth/withdraw")
    suspend fun withdraw(@Body request: WithdrawRequestDto): Response<BaseResponse<WithdrawResponseDto>>
}