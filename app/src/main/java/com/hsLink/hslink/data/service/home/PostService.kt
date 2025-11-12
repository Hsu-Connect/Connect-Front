package com.hsLink.hslink.data.service.home

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.response.home.PostPromotionDto
import com.hsLink.hslink.data.dto.response.home.PostResponseDto
import retrofit2.http.GET


interface PostService {
    @GET("posts/popular")
    suspend fun getPopularPost(): BaseResponse<PostResponseDto>

    @GET("posts/promotion")
    suspend fun getPromotionPost(): BaseResponse<PostPromotionDto>
}