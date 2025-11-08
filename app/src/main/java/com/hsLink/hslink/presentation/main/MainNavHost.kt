package com.hsLink.hslink.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.hsLink.hslink.presentation.community.navigation.main.communityNavGraph
import com.hsLink.hslink.presentation.community.navigation.post.communityPostNavGraph
import com.hsLink.hslink.presentation.community.navigation.write.communityWriteNavGraph
import com.hsLink.hslink.presentation.home.navigation.homeNavGraph
import com.hsLink.hslink.presentation.home.navigation.searchNavGraph
import com.hsLink.hslink.presentation.mypage.navigation.main.mypageNavGraph
import com.hsLink.hslink.presentation.mypage.navigation.profile.profileEditNavGraph
import com.hsLink.hslink.presentation.mypage.navigation.career.careerNavGraph

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
        communityNavGraph(
            padding,
            navigateUp = navigator::navigateUp,
            navigateToWriting = navigator::navigateWriteCommunity,
            navigateToPost = navigator::navigateToCommunityPost
        )
        mypageNavGraph(
            padding = padding,
            navController = navigator.navController
        )

        communityWriteNavGraph(
            padding = padding,
            navigateUp = navigator::navigateUp,
            navigateToCommunity = navigator::navigateToCommunity
        )

        communityPostNavGraph(
            padding = padding,
            navigateUp = navigator::navigateUp
        )

        profileEditNavGraph(
            padding = padding,
            navController = navigator.navController,
            navigateUp = navigator::navigateUp
        )

        careerNavGraph(
            padding = padding,
            navController = navigator.navController
        )
    }
}