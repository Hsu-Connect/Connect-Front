package com.hsLink.hslink.presentation.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SearchRoute(
    paddingValues: PaddingValues,
) {
    SearchScreen(paddingValues)
}

@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Search",
        modifier = modifier
            .padding(paddingValues)
    )

}