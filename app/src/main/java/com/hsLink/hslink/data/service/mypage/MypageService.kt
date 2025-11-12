// data/service/mypage/MypageService.kt
package com.hsLink.hslink.data.service.mypage

import com.hsLink.hslink.core.network.BaseResponse
import com.hsLink.hslink.data.dto.response.mypage.MyPageUserProfileDto
import com.hsLink.hslink.data.dto.response.mypage.UserProfileDto
import retrofit2.Response
import retrofit2.http.GET

interface MypageService {
    @GET("users/myprofile")
    suspend fun getUserProfile(): Response<BaseResponse<MyPageUserProfileDto>>
}