package com.hsLink.hslink.data.service.commuunity

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.request.PostRequestDto
import com.hsLink.hslink.data.dto.response.CommunityPostResponseDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface CommunityPostService {
    @POST("posts")
    suspend fun postCommunity(
        @Header("Authorization") token: String,
        @Body requestBody: PostRequestDto,
    ): BaseResponse<CommunityPostResponseDto>
}