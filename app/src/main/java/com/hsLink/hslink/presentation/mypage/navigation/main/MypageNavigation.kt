package com.hsLink.hslink.presentation.mypage.navigation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.MainTabRoute
import com.hsLink.hslink.core.navigation.Route
import com.hsLink.hslink.presentation.mypage.screen.main.MypageRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMypage(navOptions: NavOptions? = null) {
    navigate(Mypage, navOptions)
}

fun NavGraphBuilder.mypageNavGraph(
    padding: PaddingValues,
    navController: NavController,
) {
    composable<Mypage> {
        MypageRoute(
            paddingValues = padding,
            navController = navController
        )
    }
}

@Serializable
data object Mypage : MainTabRoute