package com.hsLink.hslink.core.designsystem.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object HsLinkTheme {
    val colors: HsLinkColor
        @Composable
        @ReadOnlyComposable
        get() = LocalHsLinkColors.current

    val typography: HsLinkTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalHsLinkTypography.current
}

@Composable
fun ProvideHsLinkColorsAndTypography(
    colors: HsLinkColor,
    typography: HsLinkTypography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalHsLinkColors provides colors,
        LocalHsLinkTypography provides typography,
        content = content
    )
}

@Composable
fun HsLinkTheme(
    content: @Composable () -> Unit,
) {
    ProvideHsLinkColorsAndTypography(
        colors = defaultHsLinkColor,
        typography = defaultHsLinkTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = false
                }
            }
        }
        MaterialTheme(
            content = content
        )
    }
}