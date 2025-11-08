package com.hsLink.hslink.presentation.mypage.navigation.sns

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.Route
import com.hsLink.hslink.presentation.mypage.screen.SNS.SNSEditRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSNSEdit(navOptions: NavOptions? = null) {
    navigate(SNSEdit, navOptions)
}

fun NavGraphBuilder.snsNavGraph(
    padding: PaddingValues,
    navController: NavController,
) {
    composable<SNSEdit> {
        SNSEditRoute(
            paddingValues = padding,
            navController = navController
        )
    }
}

@Serializable
data object SNSEdit : Route