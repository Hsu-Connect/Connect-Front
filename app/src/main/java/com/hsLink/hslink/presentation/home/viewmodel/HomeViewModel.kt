package com.hsLink.hslink.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsLink.hslink.domain.repository.PostRepository
import com.hsLink.hslink.presentation.home.state.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postRepository: PostRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeContract())
    val state: StateFlow<HomeContract> = _state.asStateFlow()

    init {
        fetchPopularPosts()
        fetchPromotionPosts()
    }

    fun fetchPopularPosts() {
        viewModelScope.launch {
            postRepository.getPopularPost()
                .onSuccess { posts ->
                    _state.update { state ->
                        state.copy(
                            postPopular = posts,
                            isLoading = false,
                            error = null
                        )
                    }
                }
                .onFailure { e ->
                    _state.update { state ->
                        state.copy(
                            error = e.message,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun fetchPromotionPosts() {
        viewModelScope.launch {
            postRepository.getPromotionPost()
                .onSuccess { posts ->
                    _state.update { state ->
                        state.copy(
                            postPromotion = posts,
                            isLoading = false,
                            error = null
                        )
                    }
                }
                .onFailure { e ->
                    _state.update { state ->
                        state.copy(
                            error = e.message,
                            isLoading = false
                        )
                    }
                }
        }
    }


}