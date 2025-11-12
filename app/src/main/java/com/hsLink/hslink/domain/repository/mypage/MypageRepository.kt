// domain/repository/mypage/MypageRepository.kt
package com.hsLink.hslink.domain.repository.mypage

import com.hsLink.hslink.data.dto.response.mypage.MyPageUserProfileDto
import com.hsLink.hslink.data.dto.response.mypage.UserProfileDto

interface MypageRepository {
    suspend fun getUserProfile(): Result<MyPageUserProfileDto>
}