// domain/repository/mypage/MypageRepository.kt
package com.hsLink.hslink.domain.repository.mypage

import com.hsLink.hslink.data.dto.request.mypage.UpdateProfileRequestDto
import com.hsLink.hslink.data.dto.response.mypage.MyPageUserProfileDto
import com.hsLink.hslink.data.dto.response.mypage.MyPageUserSummaryDto
import com.hsLink.hslink.data.dto.response.mypage.UserProfileDto

interface MypageRepository {
    suspend fun getUserProfile(): Result<MyPageUserProfileDto>

    suspend fun updateProfile(request: UpdateProfileRequestDto): Result<Unit>

    suspend fun getUserSummary(): Result<MyPageUserSummaryDto>

}