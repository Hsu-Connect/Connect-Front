package com.hsLink.hslink.data.remote.datasource

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.request.community.PostRequestDto
import com.hsLink.hslink.data.dto.response.community.CommunityPostResponseDto

interface CommunityPostDataSource {
    suspend fun createCommunityPost(
        request : PostRequestDto
    ): BaseResponse<CommunityPostResponseDto>
}