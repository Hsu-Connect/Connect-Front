package com.hsLink.hslink.data.repositoryimpl

import com.hsLink.hslink.data.dto.request.PostRequestDto
import com.hsLink.hslink.data.dto.response.CommunityPostResponseDto
import com.hsLink.hslink.data.remote.datasourceimpl.CommunityPostDataSourceImpl
import com.hsLink.hslink.domain.repository.community.CommunityRepository
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val communityPostDataSourceImpl: CommunityPostDataSourceImpl,
) : CommunityRepository {
    override suspend fun createCommunityPost(communityRequestDto: PostRequestDto): Result<CommunityPostResponseDto> = runCatching {
        val response = communityPostDataSourceImpl.createCommunityPost(communityRequestDto)
        if (response.isSuccess) {
            response.result
        } else {
            throw Exception(response.message)
        }
    }
}