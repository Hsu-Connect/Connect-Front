package com.hsLink.hslink.domain.repository.onboarding

import com.hsLink.hslink.data.dto.request.onboarding.CareerRequest
import com.hsLink.hslink.data.dto.request.onboarding.LinkRequest
import com.hsLink.hslink.data.dto.request.onboarding.OnboardingRequest
import com.hsLink.hslink.domain.model.search.CareerItemEntity
import com.hsLink.hslink.domain.model.search.LinkItemEntity

interface OnboardingRepository {
    suspend fun submitOnboarding(request: OnboardingRequest): Result<Unit>
    suspend fun getCareers(): Result<List<CareerItemEntity>>
    suspend fun submitCareer(request: CareerRequest): Result<CareerItemEntity>
    suspend fun getLinks(): Result<List<LinkItemEntity>>
    suspend fun submitLink(request: LinkRequest): Result<LinkItemEntity>
}