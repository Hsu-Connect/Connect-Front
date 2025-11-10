package com.hsLink.hslink.domain.repository.community

import com.hsLink.hslink.data.dto.request.PostRequestDto
import com.hsLink.hslink.data.dto.response.CommunityPostResponseDto

interface CommunityRepository {
    suspend fun createCommunityPost(communityRequestDto: PostRequestDto): Result<CommunityPostResponseDto>
}
