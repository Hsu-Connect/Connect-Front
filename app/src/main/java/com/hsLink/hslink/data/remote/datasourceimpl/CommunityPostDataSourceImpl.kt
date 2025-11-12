package com.hsLink.hslink.data.remote.datasourceimpl

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.request.community.PostRequestDto
import com.hsLink.hslink.data.dto.response.community.CommunityPostResponseDto
import com.hsLink.hslink.data.remote.datasource.CommunityPostDataSource
import com.hsLink.hslink.data.service.commuunity.CommunityPostService
import javax.inject.Inject

class CommunityPostDataSourceImpl @Inject constructor(
    private val communityPostService: CommunityPostService
) : CommunityPostDataSource {
    override suspend fun createCommunityPost(
        request : PostRequestDto
    ) : BaseResponse<CommunityPostResponseDto> {
        return communityPostService.postCommunity(requestBody = request)
    }
}