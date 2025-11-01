package com.hsLink.hslink.presentation.community.navigation.write

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.Route
import com.hsLink.hslink.presentation.community.screen.write.CommunityWritingRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToWriteCommunity(navOptions: NavOptions? = null) {
    navigate(CommunityWrite, navOptions)
}

fun NavGraphBuilder.communityWriteNavGraph(
    padding: PaddingValues,
    navigateUp: () -> Unit,
    navigateToCommunity: () -> Unit,
) {
    composable<CommunityWrite> {
        CommunityWritingRoute(
            paddingValues = padding,
            navigateUp = navigateUp,
            navigateToCommunity = navigateToCommunity
        )
    }
}

@Serializable
data object CommunityWrite : Route