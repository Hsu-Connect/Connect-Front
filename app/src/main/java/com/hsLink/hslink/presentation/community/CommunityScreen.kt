package com.hsLink.hslink.presentation.community

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun CommunityRoute(
    paddingValues: PaddingValues,
) {
    CommunityScreen(paddingValues)
}

@Composable
fun CommunityScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Community Screen",
        modifier = modifier
            .padding(paddingValues)
    )
}