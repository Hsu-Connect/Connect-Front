package com.hsLink.hslink.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme


@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
) {
    HsLinkTheme {
        HomeScreen(paddingValues)
    }
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Home Screen",
        style = HsLinkTheme.typography.body_16Normal,
        color = HsLinkTheme.colors.SkyBlue100,
        modifier = modifier
            .padding(paddingValues)
    )
}