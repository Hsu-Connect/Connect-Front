package com.hsLink.hslink.presentation.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.MainTabRoute
import com.hsLink.hslink.presentation.mypage.MypageRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMypage(navOptions: NavOptions? = null) {
    navigate(Mypage, navOptions)
}

fun NavGraphBuilder.mypageNavGraph(
    padding: PaddingValues,
) {
    composable<Mypage> {
        MypageRoute(padding)
    }
}

@Serializable
data object Mypage : MainTabRoute