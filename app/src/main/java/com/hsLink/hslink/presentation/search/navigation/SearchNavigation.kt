package com.hsLink.hslink.presentation.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.MainTabRoute
import com.hsLink.hslink.presentation.search.screen.SearchRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    navigate(Search, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    paddingValues: PaddingValues,
    onNavigateToProfile: (Long) -> Unit = {}
) {
    composable<Search> {
        SearchRoute(
            paddingValues = paddingValues,
            onNavigateToProfile = onNavigateToProfile
        )
    }
}

@Serializable
data object Search : MainTabRoute