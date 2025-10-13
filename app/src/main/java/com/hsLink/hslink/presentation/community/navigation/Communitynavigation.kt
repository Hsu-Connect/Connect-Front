package com.hsLink.hslink.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.MainTabRoute
import com.hsLink.hslink.presentation.community.CommunityRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToCommunity(navOptions: NavOptions? = null) {
    navigate(Community, navOptions)
}

fun NavGraphBuilder.communityNavGraph(
    padding: PaddingValues,
) {
    composable<Community> {
        CommunityRoute(padding)
    }
}

@Serializable
data object Community : MainTabRoute