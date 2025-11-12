package com.hsLink.hslink.domain.repository.community

import androidx.paging.PagingData
import com.hsLink.hslink.data.dto.request.community.PostRequestDto
import com.hsLink.hslink.data.dto.response.community.CommunityDetailResponseDto
import com.hsLink.hslink.data.dto.response.community.CommunityPostResponseDto
import com.hsLink.hslink.domain.model.community.CommunityPost
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    suspend fun createCommunityPost(communityRequestDto: PostRequestDto): Result<CommunityPostResponseDto>

    fun getCommunityPosts(type: String): Flow<PagingData<CommunityPost>>

    suspend fun getCommunityDetail(postId: Int): Result<CommunityDetailResponseDto>
}
