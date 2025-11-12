package com.hsLink.hslink.data.remote.datasourceimpl

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.request.onboarding.*
import com.hsLink.hslink.data.remote.datasource.OnboardingDataSource
import com.hsLink.hslink.data.service.onboarding.OnboardingService
import com.hsLink.hslink.data.dto.response.onboarding.*
import javax.inject.Inject

class OnboardingDataSourceImpl @Inject constructor(
    private val onboardingService: OnboardingService
) : OnboardingDataSource {
    override suspend fun submitOnboarding(request: OnboardingRequest): BaseResponse<Unit> =
        onboardingService.onboarding(request)

    override suspend fun getCareers(): BaseResponse<List<CareerResponse>> =
        onboardingService.getCareers()

    override suspend fun submitCareer(request: CareerRequest): BaseResponse<CareerResponse> =
        onboardingService.submitCareer(request)

    override suspend fun getLinks(): BaseResponse<LinkListResponseDto> =
        onboardingService.getLinks()

    override suspend fun submitLink(request: LinkRequest): LinkResponse =
        onboardingService.submitLink(request)
}