package com.hsLink.hslink.data.remote.datasourceimpl

import com.hsLink.hslink.data.dto.response.search.MentorListResponseDto
import com.hsLink.hslink.data.dto.response.mypage.UserProfileResponseDto
import com.hsLink.hslink.data.remote.datasource.SearchDataSource
import com.hsLink.hslink.data.service.search.SearchService
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchService: SearchService
) : SearchDataSource {

    override suspend fun getMentors(page: Int, size: Int): MentorListResponseDto {
        return searchService.getMentors(page, size)
    }

    override suspend fun getUserProfile(userId: Long): UserProfileResponseDto {
        return searchService.getUserProfile(userId)
    }
}