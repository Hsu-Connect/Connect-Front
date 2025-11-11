package com.hsLink.hslink.presentation.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.Route
import com.hsLink.hslink.presentation.login.screen.KaKaoLoginScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    navigate(Login, navOptions)
}

fun NavGraphBuilder.loginNavGraph(
    padding: PaddingValues,
    onNavigateToMain: () -> Unit,
    onNavigateToOnboarding: () -> Unit
) {
    composable<Login> {
        KaKaoLoginScreen(
            paddingValues = padding,
            onNavigateToHome = onNavigateToMain,
            onNavigateToOnboarding = onNavigateToOnboarding
        )
    }
}

@Serializable
data object Login : Route  // Route 인터페이스 구현