package com.hsLink.hslink.domain.repository

import com.hsLink.hslink.data.dto.request.onboarding.CareerUpdateRequestDto
import com.hsLink.hslink.data.dto.response.onboarding.CareerDto

interface CareerRepository {
    suspend fun getCareer(careerId: Long): Result<CareerDto>
    suspend fun getMyCareers(): Result<List<CareerDto>>
    suspend fun updateCareer(careerId: Long, request: CareerUpdateRequestDto): Result<CareerDto>
}