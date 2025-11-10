package com.hsLink.hslink.domain.repository

import com.hsLink.hslink.domain.model.PostPopularEntity
import com.hsLink.hslink.domain.model.PostPromotionEntity

interface PostRepository {
    suspend fun getPopularPost(): Result<List<PostPopularEntity>>
    suspend fun getPromotionPost(): Result<List<PostPromotionEntity>>
}