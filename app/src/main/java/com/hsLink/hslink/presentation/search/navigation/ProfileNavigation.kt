package com.hsLink.hslink.presentation.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.hsLink.hslink.core.navigation.Route
import com.hsLink.hslink.presentation.search.screen.ProfileRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToProfile(
    userId: Long,
    navOptions: NavOptions? = null
) {
    navigate(Profile(userId = userId), navOptions)
}

fun NavGraphBuilder.profileNavGraph(
    paddingValues: PaddingValues,
    onNavigateBack: () -> Unit
) {
    composable<Profile> { backStackEntry ->
        val profile = backStackEntry.toRoute<Profile>()
        ProfileRoute(
            userId = profile.userId,
            paddingValues = paddingValues,
            onNavigateBack = onNavigateBack
        )
    }
}

@Serializable
data class Profile(val userId: Long) : Route