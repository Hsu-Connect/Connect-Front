package com.hsLink.hslink.presentation.community.navigation.post

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.Route
import com.hsLink.hslink.presentation.community.screen.post.CommunityPostRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToCommunityPost(
    postId: Int,
    navOptions: NavOptions? = null
) {
    navigate(CommunityPost(postId), navOptions)
}

fun NavGraphBuilder.communityPostNavGraph(
    padding: PaddingValues,
    navigateUp: () -> Unit,
) {
    composable<CommunityPost> { backStackEntry ->

        val postId = backStackEntry.arguments?.getInt("postId") ?: -1

        CommunityPostRoute(
            postId = postId,
            paddingValues = padding,
            navigateUp = navigateUp
        )
    }
}

@Serializable
data class CommunityPost(val postId: Int) : Route