package com.hsLink.hslink.presentation.search.state

import com.hsLink.hslink.domain.model.search.MentorEntity

data class SearchUiState(
    val isLoading: Boolean = false,
    val mentors: List<MentorEntity> = emptyList(),
    val totalMentorCount: Long = 0,
    val currentPage: Int = 0,
    val isLoadingMore: Boolean = false,
    val errorMessage: String? = null
)

sealed class SearchIntent {
    object LoadMentors : SearchIntent()
    object LoadMoreMentors : SearchIntent()
    data class NavigateToProfile(val userId: Long) : SearchIntent()
    object ClearError : SearchIntent()
}

sealed class SearchSideEffect {
    data class NavigateToProfile(val userId: Long) : SearchSideEffect()
    data class ShowError(val message: String) : SearchSideEffect()
}