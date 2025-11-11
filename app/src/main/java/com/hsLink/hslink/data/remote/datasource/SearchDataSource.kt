package com.hsLink.hslink.data.remote.datasource

import com.hsLink.hslink.data.dto.response.MentorListResponseDto
import com.hsLink.hslink.data.dto.response.UserProfileResponseDto

interface SearchDataSource {
    suspend fun getMentors(page: Int, size: Int): MentorListResponseDto
    suspend fun getUserProfile(userId: Long): UserProfileResponseDto
}