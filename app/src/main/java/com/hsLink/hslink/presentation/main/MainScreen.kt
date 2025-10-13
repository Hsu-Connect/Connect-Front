package com.hsLink.hslink.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.hsLink.hslink.presentation.main.component.MainBottomBar
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        bottomBar = {
            MainBottomBar(
                modifier = Modifier.background(color = Color.White),
                isVisible = navigator.showBottomNavigator(),
                tabs = MainTab.entries.toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = navigator::navigate
            )
        }
    ) { padding ->
        MainNavHost(
            navigator = navigator,
            padding = padding,
            modifier = Modifier
        )
    }
}