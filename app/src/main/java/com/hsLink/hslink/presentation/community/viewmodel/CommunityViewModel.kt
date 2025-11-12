package com.hsLink.hslink.presentation.community.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hsLink.hslink.data.dto.request.community.PostRequestDto
import com.hsLink.hslink.domain.model.community.CommunityPost
import com.hsLink.hslink.domain.repository.community.CommunityRepository
import com.hsLink.hslink.presentation.community.component.CommunityTab
import com.hsLink.hslink.presentation.community.state.CommunityDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val repository: CommunityRepository
) : ViewModel() {

    private val _selectedTab = MutableStateFlow(CommunityTab.Popular)
    val selectedTab: Flow<CommunityTab> = _selectedTab

    private val _postDetailState = MutableStateFlow<CommunityDetailState>(CommunityDetailState.Loading)
    val postDetailState: StateFlow<CommunityDetailState> = _postDetailState

    val communityPosts: Flow<PagingData<CommunityPost>> = _selectedTab
        .flatMapLatest { tab ->
            repository.getCommunityPosts(tab.name.lowercase())
        }
        .cachedIn(viewModelScope)

    fun selectTab(tab: CommunityTab) {
        _selectedTab.value = tab
    }

    fun createPost(postType: String, title: String, body: String) {
        viewModelScope.launch {
            val request = PostRequestDto(
                postType = postType,
                title = title,
                body = body
            )
            repository.createCommunityPost(request)
                .onSuccess {
                    // Handle success
                }
                .onFailure {
                    // Handle failure
                }
        }
    }

    fun getPostDetail(postId: Int) {
        viewModelScope.launch {
            _postDetailState.value = CommunityDetailState.Loading
            repository.getCommunityDetail(postId)
                .onSuccess { response ->
                    _postDetailState.value = CommunityDetailState.Success(response)
                }
                .onFailure { error ->
                    _postDetailState.value = CommunityDetailState.Error(error.message ?: "게시물 상세 조회 실패")
                }
        }
    }
}