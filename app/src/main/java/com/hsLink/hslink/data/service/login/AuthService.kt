package com.hsLink.hslink.data.service.login

import com.hsLink.hslink.data.dto.request.SocialLoginRequestDto
import com.hsLink.hslink.data.dto.response.SocialLoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun socialLogin(@Body request: SocialLoginRequestDto): Response<SocialLoginResponseDto>
}