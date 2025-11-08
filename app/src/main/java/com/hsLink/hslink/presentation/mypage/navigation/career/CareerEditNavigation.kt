package com.hsLink.hslink.presentation.mypage.navigation.career

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.Route
import com.hsLink.hslink.presentation.mypage.screen.career.CareerEditRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToCareerEdit(navOptions: NavOptions? = null) {
    navigate(CareerEdit, navOptions)
}

fun NavGraphBuilder.careerNavGraph(
    padding: PaddingValues,
    navController: NavController,
) {
    composable<CareerEdit> {
        CareerEditRoute(
            paddingValues = padding,
            navController = navController
        )
    }
}

@Serializable
data object CareerEdit : Route