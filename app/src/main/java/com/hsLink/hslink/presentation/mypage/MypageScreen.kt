package com.hsLink.hslink.presentation.mypage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MypageRoute(
    paddingValues: PaddingValues,
) {
    MypageScreen(paddingValues)
}

@Composable
fun MypageScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "My Page",
        modifier = modifier
            .padding(paddingValues)
    )
}