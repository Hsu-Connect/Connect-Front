package com.hsLink.hslink.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.hsLink.hslink.R
import com.hsLink.hslink.core.navigation.MainTabRoute
import com.hsLink.hslink.presentation.home.navigation.Community
import com.hsLink.hslink.presentation.home.navigation.Home
import com.hsLink.hslink.presentation.home.navigation.Search
import com.hsLink.hslink.presentation.mypage.navigation.Mypage

enum class MainTab(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val contentDescription: Int,
    val route: MainTabRoute,
) {

    HOME(
        selectedIcon = R.drawable.ic_mypage,
        unselectedIcon = R.drawable.ic_mypage,
        contentDescription =R.string.bottom_navigation_item_home,
        route = Home
    ),

    SEARCH(
        selectedIcon = R.drawable.ic_mypage,
        unselectedIcon = R.drawable.ic_mypage,
        contentDescription = R.string.bottom_navigation_item_search,
        route = Search
    ),

    COMMUNITY(
        selectedIcon = R.drawable.ic_mypage,
        unselectedIcon = R.drawable.ic_mypage,
        contentDescription = R.string.bottom_navigation_item_community,
        route = Community
    ),

    MYPAGE(
        selectedIcon = R.drawable.ic_mypage,
        unselectedIcon = R.drawable.ic_mypage,
        contentDescription = R.string.bottom_navigation_item_mypage,
        route = Mypage
    );


    companion object {

        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (MainTabRoute) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}