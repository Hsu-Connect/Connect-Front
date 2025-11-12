package com.hsLink.hslink.data.remote.datasource

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.request.onboarding.*
import com.hsLink.hslink.data.dto.response.onboarding.*

interface OnboardingDataSource {
    suspend fun submitOnboarding(request: OnboardingRequest): BaseResponse<Unit>
    suspend fun getCareers(): BaseResponse<List<CareerResponse>>
    suspend fun submitCareer(request: CareerRequest): BaseResponse<CareerResponse>
    suspend fun getLinks(): BaseResponse<LinkListResponseDto>
    suspend fun submitLink(request: LinkRequest): LinkResponse
}