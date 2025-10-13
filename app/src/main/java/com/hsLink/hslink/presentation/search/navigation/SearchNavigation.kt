package com.hsLink.hslink.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.MainTabRoute
import com.hsLink.hslink.presentation.search.SearchRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    navigate(Search, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    padding: PaddingValues,
) {
    composable<Search> {
        SearchRoute(padding)
    }
}

@Serializable
data object Search : MainTabRoute