package com.hsLink.hslink.presentation.community.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsLink.hslink.data.dto.request.PostRequestDto
import com.hsLink.hslink.domain.repository.community.CommunityRepository
import com.hsLink.hslink.presentation.community.state.CommunityContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val repository: CommunityRepository
) : ViewModel(){

    private val _state = MutableStateFlow(CommunityContract())
    val state: StateFlow<CommunityContract> = _state.asStateFlow()
    
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
}