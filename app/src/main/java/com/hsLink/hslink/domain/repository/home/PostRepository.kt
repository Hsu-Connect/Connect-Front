package com.hsLink.hslink.domain.repository.home

import com.hsLink.hslink.domain.model.home.PostPopularEntity
import com.hsLink.hslink.domain.model.home.PostPromotionEntity

interface PostRepository {
    suspend fun getPopularPost(): Result<List<PostPopularEntity>>
    suspend fun getPromotionPost(): Result<List<PostPromotionEntity>>
}