// data/service/CareerService.kt
package com.hsLink.hslink.data.service

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.request.onboarding.CareerUpdateRequestDto
import com.hsLink.hslink.data.dto.response.onboarding.CareerDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface CareerService {

    @GET("careers/mycareers")
    suspend fun getMyCareers(): BaseResponse<List<CareerDto>>

    @GET("careers/{careerId}")
    suspend fun getCareer(
        @Path("careerId") careerId: Long
    ): BaseResponse<CareerDto>

    @PUT("careers/{careerId}")
    suspend fun updateCareer(
        @Path("careerId") careerId: Long,
        @Body request: CareerUpdateRequestDto
    ): BaseResponse<CareerDto>
}