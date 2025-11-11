package com.hsLink.hslink.data.repositoryimpl.search

import android.util.Log
import com.hsLink.hslink.data.remote.datasource.SearchDataSource
import com.hsLink.hslink.domain.model.search.MentorListEntity
import com.hsLink.hslink.domain.model.search.MentorEntity
import com.hsLink.hslink.domain.model.search.UserProfileEntity
import com.hsLink.hslink.domain.model.search.CareerEntity
import com.hsLink.hslink.domain.model.search.LinkEntity
import com.hsLink.hslink.domain.repository.search.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {

    override suspend fun getMentors(page: Int, size: Int): Result<MentorListEntity> {
        Log.d("SearchRepository", "getMentors 호출: page=$page, size=$size")
        return try {
            val response = searchDataSource.getMentors(page, size)
            Log.d("SearchRepository", "API 응답: isSuccess=${response.isSuccess}, message=${response.message}")

            if (response.isSuccess) {
                val mentorListEntity = MentorListEntity(
                    totalMentorCount = response.result.totalMentorCount,
                    page = response.result.page,
                    size = response.result.size,
                    totalPages = response.result.totalPages,
                    mentors = response.result.items.map { dto ->
                        MentorEntity(
                            userId = dto.userId,
                            name = dto.name,
                            major = dto.major,
                            jobSeeking = dto.jobSeeking,
                            employed = dto.employed,
                            academicStatus = dto.academicStatus
                        )
                    }
                )
                Log.d("SearchRepository", "변환 완료: ${mentorListEntity.mentors.size}개 멘토")
                Result.success(mentorListEntity)
            } else {
                Log.e("SearchRepository", "API 실패: ${response.message}")
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Log.e("SearchRepository", "예외 발생: ${e.message}", e)
            Result.failure(e)
        }
    }


    override suspend fun getUserProfile(userId: Long): Result<UserProfileEntity> {
        return try {
            val response = searchDataSource.getUserProfile(userId)
            if (response.isSuccess) {
                val userProfileEntity = UserProfileEntity(
                    userId = response.result.userId,
                    name = response.result.name,
                    studentNumberPrefix = response.result.studentNumberPrefix,
                    major = response.result.major,
                    email = response.result.email,
                    jobSeeking = response.result.jobSeeking,
                    employed = response.result.employed,
                    academicStatus = response.result.academicStatus,
                    careers = response.result.careers.map { dto ->
                        CareerEntity(
                            id = dto.id,
                            companyName = dto.companyName,
                            position = dto.position,
                            jobType = dto.jobType,
                            employed = dto.employed,
                            startYm = dto.startYm,
                            endYm = dto.endYm
                        )
                    },
                    links = response.result.links.map { dto ->
                        LinkEntity(
                            id = dto.id,
                            type = dto.type,
                            url = dto.url
                        )
                    }
                )
                Result.success(userProfileEntity)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}