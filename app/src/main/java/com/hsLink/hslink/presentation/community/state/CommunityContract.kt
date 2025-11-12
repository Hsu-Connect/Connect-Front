package com.hsLink.hslink.presentation.community.state

import androidx.compose.runtime.Immutable
import com.hsLink.hslink.data.dto.response.community.CommunityDetailResponseDto
import com.hsLink.hslink.domain.model.community.CommunityPostResponseEntity

@Immutable
data class CommunityContract (
    val isLoading: Boolean = false,
    val error: String? = null,
    val communityEntity: CommunityPostResponseEntity ? = null
)


sealed interface CommunityDetailState {
    data object Loading : CommunityDetailState
    data class Success(val post: CommunityDetailResponseDto) : CommunityDetailState
    data class Error(val message: String) : CommunityDetailState
}