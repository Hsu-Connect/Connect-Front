package com.hsLink.hslink.data.repositoryimpl

import com.hsLink.hslink.data.dto.response.toEntity
import com.hsLink.hslink.data.remote.datasourceimpl.PostDataSourceImpl
import com.hsLink.hslink.domain.model.PostPopularEntity
import com.hsLink.hslink.domain.model.PostPromotionEntity
import com.hsLink.hslink.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSourceImpl: PostDataSourceImpl,
) : PostRepository {
    override suspend fun getPopularPost(): Result<List<PostPopularEntity>> =
        runCatching {
            val response = postDataSourceImpl.getPopularPost()
            if (response.isSuccess) {
                response.result.posts.map { it.toEntity() }
            } else {
                throw Exception(response.message)
            }
        }

    override suspend fun getPromotionPost(): Result<List<PostPromotionEntity>> =
        runCatching {
            val respone = postDataSourceImpl.getPromotionPost()
            if (respone.isSuccess) {
                respone.result.posts.map { it.toEntity() }
            } else {
                throw Exception(respone.message)
            }
        }
}