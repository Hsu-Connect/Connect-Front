package com.hsLink.hslink.presentation.community.navigation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.MainTabRoute
import com.hsLink.hslink.presentation.community.screen.main.CommunityRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToCommunity(navOptions: NavOptions? = null) {
    navigate(Community, navOptions)
}

fun NavGraphBuilder.communityNavGraph(
    padding: PaddingValues,
    navigateToWriteCommunity : () -> Unit,
    navigateToPost: (Int) -> Unit,
) {
    composable<Community> {
        CommunityRoute(
            paddingValues = padding,
            navigateWriteCommunity = navigateToWriteCommunity,
            navigateToPost = navigateToPost,
        )
    }
}

@Serializable
data object Community : MainTabRoute