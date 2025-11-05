package com.hsLink.hslink.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.hsLink.hslink.presentation.home.navigation.Home
import com.hsLink.hslink.presentation.community.navigation.main.navigateToCommunity
import com.hsLink.hslink.presentation.community.navigation.post.navigateToCommunityPost
import com.hsLink.hslink.presentation.home.navigation.navigateToHome
import com.hsLink.hslink.presentation.home.navigation.navigateToSearch
import com.hsLink.hslink.presentation.community.navigation.write.navigateToWriteCommunity
import com.hsLink.hslink.presentation.mypage.navigation.navigateToMypage
import com.hsLink.hslink.presentation.onboarding.navigation.Onboarding

class MainNavigator(
    val navController: NavHostController,
) {

    val startDestination = Onboarding

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    val currentTab: MainTab?
        @Composable get() = MainTab.find { tabRoute ->
            currentDestination?.hasRoute(tabRoute::class) == true
        }

    fun navigate(tabRoute: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tabRoute) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.SEARCH -> navController.navigateToSearch(navOptions)
            MainTab.COMMUNITY -> navController.navigateToCommunity(navOptions)
            MainTab.MYPAGE -> navController.navigateToMypage(navOptions)
        }
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateWriteCommunity(navOptions: NavOptions? = null) {
        navController.navigateToWriteCommunity(navOptions)
    }

    fun navigateToCommunity(navOptions: NavOptions? = null) {
        navController.navigateToCommunity(navOptions)
    }

    fun navigateToCommunityPost(postId: String, navOptions: NavOptions? = null) {
        navController.navigateToCommunityPost(postId, navOptions)
    }

    fun navigateToHome(navOptions: NavOptions? = null){
        navController.navigateToHome(navOptions)
    }

    @Composable
    fun showBottomNavigator() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}