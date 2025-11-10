package com.hsLink.hslink.data.remote.datasourceimpl

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.response.PostPromotionDto
import com.hsLink.hslink.data.dto.response.PostResponseDto
import com.hsLink.hslink.data.remote.datasource.PostDataSource
import com.hsLink.hslink.data.service.home.PostService
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(
    private val postService: PostService
): PostDataSource{
    override suspend fun getPopularPost() : BaseResponse<PostResponseDto>{
        return postService.getPopularPost()
    }

    override suspend fun getPromotionPost(): BaseResponse<PostPromotionDto> {
        return postService.getPromotionPost()
    }
}