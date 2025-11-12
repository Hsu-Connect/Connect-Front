// data/repositoryimpl/mypage/MypageRepositoryImpl.kt
package com.hsLink.hslink.data.repositoryimpl.mypage

import com.hsLink.hslink.data.dto.request.mypage.UpdateProfileRequestDto
import com.hsLink.hslink.data.dto.response.mypage.MyPageUserProfileDto
import com.hsLink.hslink.data.dto.response.mypage.UserProfileDto
import com.hsLink.hslink.data.service.mypage.MypageService
import com.hsLink.hslink.domain.repository.mypage.MypageRepository
import javax.inject.Inject

class MypageRepositoryImpl @Inject constructor(
    private val mypageService: MypageService
) : MypageRepository {

    override suspend fun getUserProfile(): Result<MyPageUserProfileDto> {
        return try {
            val response = mypageService.getUserProfile()
            if (response.isSuccessful) {
                response.body()?.let { baseResponse ->
                    if (baseResponse.isSuccess) {
                        Result.success(baseResponse.result)
                    } else {
                        Result.failure(Exception(baseResponse.message))
                    }
                } ?: Result.failure(Exception("응답이 비어있습니다"))
            } else {
                Result.failure(Exception("API 호출 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateProfile(request: UpdateProfileRequestDto): Result<Unit> {
        return try {
            val response = mypageService.updateProfile(request)
            if (response.isSuccess) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}