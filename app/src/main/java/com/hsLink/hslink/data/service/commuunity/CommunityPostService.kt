package com.hsLink.hslink.data.service.commuunity

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.request.community.PostRequestDto
import com.hsLink.hslink.data.dto.response.community.CommunityListResponseDto
import com.hsLink.hslink.data.dto.response.community.CommunityPostResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommunityPostService {
    @POST("posts")
    suspend fun postCommunity(
        @Body requestBody: PostRequestDto,
    ): BaseResponse<CommunityPostResponseDto>

    @GET("posts")
    suspend fun getCommunity(
        @Query("type") type: String,
        @Query("page") page: Int,
    ): BaseResponse<CommunityListResponseDto>
}