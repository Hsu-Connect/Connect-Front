package com.hsLink.hslink.presentation.community.state

import androidx.compose.runtime.Immutable
import com.hsLink.hslink.domain.model.community.CommunityPostResponseEntity

@Immutable
data class CommunityContract (
    val isLoading: Boolean = false,
    val error: String? = null,
    val communityEntity: CommunityPostResponseEntity ? = null
)