package com.hsLink.hslink.presentation.home.state

import androidx.compose.runtime.Immutable
import com.hsLink.hslink.domain.model.home.PostPopularEntity
import com.hsLink.hslink.domain.model.home.PostPromotionEntity

@Immutable
data class HomeContract (
    val isLoading: Boolean = false,
    val error: String? = null,
    val postPopular : List<PostPopularEntity> = emptyList(),
    val postPromotion : List<PostPromotionEntity> = emptyList()
){

}