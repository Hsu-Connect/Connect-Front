package com.hsLink.hslink.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.hsLink.hslink.presentation.home.navigation.communityNavGraph
import com.hsLink.hslink.presentation.home.navigation.homeNavGraph
import com.hsLink.hslink.presentation.home.navigation.searchNavGraph
import com.hsLink.hslink.presentation.mypage.navigation.mypageNavGraph

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination,
        modifier = modifier

    ) {
        homeNavGraph(padding)
        searchNavGraph(padding)
        communityNavGraph(padding)
        mypageNavGraph(padding)
    }
}