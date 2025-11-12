package com.hsLink.hslink.data.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hsLink.hslink.data.dto.request.community.PostRequestDto
import com.hsLink.hslink.data.dto.response.community.CommunityDetailResponseDto
import com.hsLink.hslink.data.dto.response.community.CommunityPostResponseDto
import com.hsLink.hslink.data.paging.CommunityPagingSource
import com.hsLink.hslink.data.service.commuunity.CommunityPostService
import com.hsLink.hslink.domain.model.community.CommunityPost
import com.hsLink.hslink.domain.repository.community.CommunityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val communityPostService: CommunityPostService,
) : CommunityRepository {
    override suspend fun createCommunityPost(communityRequestDto: PostRequestDto): Result<CommunityPostResponseDto> =
        runCatching {
            val response = communityPostService.postCommunity(communityRequestDto)
            if (response.isSuccess) {
                response.result
            } else {
                throw Exception(response.message)
            }
        }

    override fun getCommunityPosts(type: String): Flow<PagingData<CommunityPost>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { CommunityPagingSource(communityPostService, type) }
        ).flow
    }

    override suspend fun getCommunityDetail(postId: Int): Result<CommunityDetailResponseDto> =
        runCatching {
            val response = communityPostService.getCommunityDetail(postId)
            if (response.isSuccess) {
                response.result
            } else {
                throw Exception(response.message)
            }
        }
}