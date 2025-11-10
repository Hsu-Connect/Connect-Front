package com.hsLink.hslink.domain.model.home

data class PostPopularEntity (
    val id: Int,
    val title: String,
)

data class PostPromotionEntity(
    val id: Int,
    val title: String,
    val summary: String,
    val author: String,
    val studentId: String,
    val authorStatus: String,
)