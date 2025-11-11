package com.hsLink.hslink.domain.repository.search

import com.hsLink.hslink.domain.model.search.MentorListEntity
import com.hsLink.hslink.domain.model.search.UserProfileEntity

interface SearchRepository {
    suspend fun getMentors(page: Int, size: Int): Result<MentorListEntity>
    suspend fun getUserProfile(userId: Long): Result<UserProfileEntity>
}