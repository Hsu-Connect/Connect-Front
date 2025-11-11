package com.hsLink.hslink.presentation.search.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsLink.hslink.domain.repository.search.SearchRepository
import com.hsLink.hslink.presentation.search.state.SearchIntent
import com.hsLink.hslink.presentation.search.state.SearchSideEffect
import com.hsLink.hslink.presentation.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SearchSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun handleIntent(intent: SearchIntent) {
        Log.d("SearchViewModel", "handleIntent: $intent")
        when (intent) {
            is SearchIntent.LoadMentors -> loadMentors()
            is SearchIntent.LoadMoreMentors -> loadMoreMentors()
            is SearchIntent.NavigateToProfile -> navigateToProfile(intent.userId)
            is SearchIntent.ClearError -> clearError()
        }
    }

    private fun loadMentors() {
        Log.d("SearchViewModel", "loadMentors 시작")
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )
            Log.d("SearchViewModel", "Loading 상태 설정 완료")

            searchRepository.getMentors(page = 0, size = 15)
                .onSuccess { mentorList ->
                    Log.d("SearchViewModel", "API 성공: ${mentorList.mentors.size}개 멘토 로드")
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        mentors = mentorList.mentors,
                        totalMentorCount = mentorList.totalMentorCount,
                        currentPage = 0
                    )
                }
                .onFailure { exception ->
                    Log.e("SearchViewModel", "API 실패: ${exception.message}", exception)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message
                    )
                    postSideEffect(SearchSideEffect.ShowError(exception.message ?: "Unknown error"))
                }
        }
    }
    private fun loadMoreMentors() {
        val currentState = _uiState.value
        if (currentState.isLoadingMore) return

        viewModelScope.launch {
            _uiState.value = currentState.copy(isLoadingMore = true)

            searchRepository.getMentors(page = currentState.currentPage + 1, size = 15)
                .onSuccess { mentorList ->
                    _uiState.value = _uiState.value.copy(
                        isLoadingMore = false,
                        mentors = currentState.mentors + mentorList.mentors,
                        currentPage = currentState.currentPage + 1
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(isLoadingMore = false)
                    postSideEffect(SearchSideEffect.ShowError(exception.message ?: "Load more failed"))
                }
        }
    }

    private fun navigateToProfile(userId: Long) {
        viewModelScope.launch {
            postSideEffect(SearchSideEffect.NavigateToProfile(userId))
        }
    }

    private fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    private suspend fun postSideEffect(sideEffect: SearchSideEffect) {
        _sideEffect.emit(sideEffect)
    }
}