package com.hsLink.hslink.data.remote.datasource

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.response.PostPromotionDto
import com.hsLink.hslink.data.dto.response.PostResponseDto

interface PostDataSource {
    suspend fun getPopularPost() : BaseResponse<PostResponseDto>

    suspend fun getPromotionPost() : BaseResponse<PostPromotionDto>
}