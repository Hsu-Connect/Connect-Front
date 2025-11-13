package com.hsLink.hslink.data.repositoryimpl

import com.hsLink.hslink.data.dto.request.onboarding.CareerUpdateRequestDto
import com.hsLink.hslink.data.dto.response.onboarding.CareerDto
import com.hsLink.hslink.data.service.CareerService
import com.hsLink.hslink.domain.repository.CareerRepository
import javax.inject.Inject

class CareerRepositoryImpl @Inject constructor(
    private val careerService: CareerService
) : CareerRepository {

    override suspend fun getMyCareers(): Result<List<CareerDto>> {
        return try {
            val response = careerService.getMyCareers()
            if (response.isSuccess) {
                Result.success(response.result)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateCareer(careerId: Long, request: CareerUpdateRequestDto): Result<CareerDto> {
        return try {
            val response = careerService.updateCareer(careerId, request)
            if (response.isSuccess) {
                Result.success(response.result)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun getCareer(careerId: Long): Result<CareerDto> {
        return try {
            val response = careerService.getCareer(careerId)
            if (response.isSuccess) {
                Result.success(response.result)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}