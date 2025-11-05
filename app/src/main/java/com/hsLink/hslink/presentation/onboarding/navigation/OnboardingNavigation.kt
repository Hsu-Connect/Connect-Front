package com.hsLink.hslink.presentation.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.Route
import com.hsLink.hslink.presentation.onboarding.OnboardingRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToOnboarding(
    navOptions: NavOptions? = null,
) {
    navigate(Onboarding, navOptions)
}

fun NavGraphBuilder.onboardingNavGraph(
    padding: PaddingValues,
    navigateUp: () -> Unit,
    navigateHome: () -> Unit,
) {
    composable<Onboarding> {
        OnboardingRoute(
            paddingValues = padding,
            navigateUp = navigateUp,
            navigateToHome = navigateHome
        )
    }
}

@Serializable
data object Onboarding : Route