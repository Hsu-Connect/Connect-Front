package com.hsLink.hslink.data.service.search

import com.hsLink.hslink.data.dto.response.MentorListResponseDto
import com.hsLink.hslink.data.dto.response.UserProfileResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {

    @GET("users/mentors")
    suspend fun getMentors(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 15
    ): MentorListResponseDto

    @GET("users/profiles/{userId}")
    suspend fun getUserProfile(
        @Path("userId") userId: Long
    ): UserProfileResponseDto
}