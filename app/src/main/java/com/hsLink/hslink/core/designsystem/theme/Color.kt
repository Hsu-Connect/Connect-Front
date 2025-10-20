package com.hsLink.hslink.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


val Common = Color(0xFFFFFFFF)

val Transparent = Color(0xFF2D2D2D)

val SkyBlue100 = Color(0xFFEFF8FC)
val SkyBlue200 = Color(0xFFD7EEFB)
val SkyBlue300 = Color(0xFFA4D4F0)
val SkyBlue400 = Color(0xFF7DC2E9)
val SkyBlue500 = Color(0xFF62B5E5)
val SkyBlue600 = Color(0xFF457FA0)
val SkyBlue700 = Color(0xFF225370)

val DeepBlue100 = Color(0xFFE6EDF8)
val DeepBlue200 = Color(0xFFB2CEFA)
val DeepBlue300 = Color(0xFF6B94D8)
val DeepBlue400 = Color(0xFF2B66C7)
val DeepBlue500 = Color(0xFF0047BB)
val DeepBlue600 = Color(0xFF003283)
val DeepBlue700 = Color(0xFF071F46)

val Grey100 = Color(0xFFEEEEEF)
val Grey200 = Color(0xFFD1D2D4)
val Grey300 = Color(0xFF9C9D9F)
val Grey400 = Color(0xFF717376)
val Grey500 = Color(0xFF54565A)
val Grey600 = Color(0xFF3B3C3F)
val Grey700 = Color(0xFF2D2D30)

val Red100 = Color(0xFFFCEBEB)
val Red200 = Color(0xFFFFC9C8)
val Red300 = Color(0xFFF08C8A)
val Red400 = Color(0xFFE95B57)
val Red500 = Color(0xFFE53935)
val Red600 = Color(0xFFA02825)
val Red700 = Color(0xFF651513)

@Immutable
data class HsLinkColor(
    val Common: Color,
    val Transparent: Color,
    val SkyBlue100: Color,
    val SkyBlue200: Color,
    val SkyBlue300: Color,
    val SkyBlue400: Color,
    val SkyBlue500: Color,
    val SkyBlue600: Color,
    val SkyBlue700: Color,
    val DeepBlue100: Color,
    val DeepBlue200: Color,
    val DeepBlue300: Color,
    val DeepBlue400: Color,
    val DeepBlue500: Color,
    val DeepBlue600: Color,
    val DeepBlue700: Color,
    val Grey100: Color,
    val Grey200: Color,
    val Grey300: Color,
    val Grey400: Color,
    val Grey500: Color,
    val Grey600: Color,
    val Grey700: Color,
    val Red100: Color,
    val Red200: Color,
    val Red300: Color,
    val Red400: Color,
    val Red500: Color,
    val Red600: Color,
    val Red700: Color,
)

val defaultHsLinkColor = HsLinkColor(
    Common = Common,
    Transparent = Transparent,
    SkyBlue100 = SkyBlue100,
    SkyBlue200 = SkyBlue200,
    SkyBlue300 = SkyBlue300,
    SkyBlue400 = SkyBlue400,
    SkyBlue500 = SkyBlue500,
    SkyBlue600 = SkyBlue600,
    SkyBlue700 = SkyBlue700,
    DeepBlue100 = DeepBlue100,
    DeepBlue200 = DeepBlue200,
    DeepBlue300 = DeepBlue300,
    DeepBlue400 = DeepBlue400,
    DeepBlue500 = DeepBlue500,
    DeepBlue600 = DeepBlue600,
    DeepBlue700 = DeepBlue700,
    Grey100 = Grey100,
    Grey200 = Grey200,
    Grey300 = Grey300,
    Grey400 = Grey400,
    Grey500 = Grey500,
    Grey600 = Grey600,
    Grey700 = Grey700,
    Red100 = Red100,
    Red200 = Red200,
    Red300 = Red300,
    Red400 = Red400,
    Red500 = Red500,
    Red600 = Red600,
    Red700 = Red700
)

val LocalHsLinkColors = staticCompositionLocalOf { defaultHsLinkColor }