package com.hsLink.hslink.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hsLink.hslink.data.service.commuunity.CommunityPostService
import com.hsLink.hslink.domain.model.community.CommunityPost
import com.hsLink.hslink.domain.model.community.toEntity

class CommunityPagingSource(
    private val communityPostService: CommunityPostService,
    private val type: String
) : PagingSource<Int, CommunityPost>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommunityPost> {
        val page = params.key ?: 0

        return try {
            val response = communityPostService.getCommunity(type = type, page = page)
            val posts = response.result.posts.map { it.toEntity() }

            LoadResult.Page(
                data = posts,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.result.hasNext) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CommunityPost>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}