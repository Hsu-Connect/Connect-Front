package com.hsLink.hslink.data.repositoryimpl.onboarding

import com.hsLink.hslink.data.dto.request.onboarding.*
import com.hsLink.hslink.data.dto.response.onboarding.toEntity
import com.hsLink.hslink.data.remote.datasource.OnboardingDataSource
import com.hsLink.hslink.domain.model.search.*
import com.hsLink.hslink.domain.repository.onboarding.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingDataSource: OnboardingDataSource,
) : OnboardingRepository {
    override suspend fun submitOnboarding(request: OnboardingRequest): Result<Unit> = runCatching {
        val response = onboardingDataSource.submitOnboarding(request)
        if (response.isSuccess) response.result
        else throw Exception(response.message)
    }

    override suspend fun getCareers(): Result<List<CareerItemEntity>> = runCatching {
        val response = onboardingDataSource.getCareers()
        if (response.isSuccess && response.result != null) {
            response.result.map { it.toEntity() }
        } else throw Exception(response.message ?: "Failed to get careers")
    }

    override suspend fun submitCareer(request: CareerRequest): Result<CareerItemEntity> = runCatching {
        val response = onboardingDataSource.submitCareer(request)
        if (response.isSuccess && response.result != null) {
            response.result.toEntity()
        } else throw Exception(response.message ?: "Failed to submit career")
    }

    override suspend fun getLinks(): Result<List<LinkItemEntity>> = runCatching {
        val response = onboardingDataSource.getLinks()
        if (response.isSuccess && response.result != null) {
            response.result.links.map { it.toEntity() }
        } else throw Exception(response.message ?: "Failed to get links")
    }

    override suspend fun submitLink(request: LinkRequest): Result<LinkItemEntity> = runCatching {
        onboardingDataSource.submitLink(request).toEntity()
    }
}