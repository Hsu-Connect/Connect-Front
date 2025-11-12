package com.hsLink.hslink.data.remote.datasource

import com.hsLink.hslink.data.dto.response.search.MentorListResponseDto
import com.hsLink.hslink.data.dto.response.mypage.UserProfileResponseDto

interface SearchDataSource {
    suspend fun getMentors(page: Int, size: Int): MentorListResponseDto
    suspend fun getUserProfile(userId: Long): UserProfileResponseDto
}