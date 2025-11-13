package com.hsLink.hslink.data.service.onboarding

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.request.onboarding.*
import com.hsLink.hslink.data.dto.response.onboarding.*
import retrofit2.http.*

interface OnboardingService {
    @POST("auth/onboarding")
    suspend fun onboarding(@Body request: OnboardingRequest): BaseResponse<Unit>

    @GET("careers/mycareers")
    suspend fun getCareers(): BaseResponse<List<CareerResponse>>

    @POST("careers")
    suspend fun submitCareer(@Body request: CareerRequest): BaseResponse<CareerResponse>

    @GET("links/mylinks")
    suspend fun getLinks(): BaseResponse<LinkListResponseDto>

    @POST("links")
    suspend fun submitLink(@Body request: LinkRequest): LinkResponse
}