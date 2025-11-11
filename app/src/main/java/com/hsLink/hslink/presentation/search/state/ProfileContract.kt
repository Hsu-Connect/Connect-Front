package com.hsLink.hslink.presentation.search.state

import com.hsLink.hslink.domain.model.search.UserProfileEntity

data class ProfileUiState(
    val isLoading: Boolean = false,
    val userProfile: UserProfileEntity? = null,
    val errorMessage: String? = null
)

sealed class ProfileIntent {
    data class LoadProfile(val userId: Long) : ProfileIntent()
    object ClearError : ProfileIntent()
    object NavigateBack : ProfileIntent()
}

sealed class ProfileSideEffect {
    object NavigateBack : ProfileSideEffect()
    data class ShowError(val message: String) : ProfileSideEffect()
}