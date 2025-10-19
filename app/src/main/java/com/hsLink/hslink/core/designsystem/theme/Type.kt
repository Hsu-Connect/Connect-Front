package com.hsLink.hslink.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.hsLink.hslink.R

val HsLinkSemiBoldFont = FontFamily(Font(R.font.pretendard_semibold))
val HsLinkRegularFont = FontFamily(Font(R.font.pretendard_regular))
val HsLinkMediumFont = FontFamily(Font(R.font.pretendard_medium))

@Immutable
data class HsLinkTypography(
    val title_24Strong: TextStyle,
    val title_20Strong: TextStyle,
    val title_16Strong: TextStyle,
    val title_14Strong: TextStyle,

    val body_16Strong: TextStyle,
    val body_16Normal: TextStyle,
    val body_14Normal: TextStyle,

    val caption_14Normal: TextStyle,
    val caption_12Normal: TextStyle,

    val btm_L: TextStyle,
    val btm_M: TextStyle,
    val btm_S: TextStyle,
)

val defaultHsLinkTypography = HsLinkTypography(

    title_24Strong = TextStyle(
        fontFamily = HsLinkSemiBoldFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.03).em
    ),
    title_20Strong = TextStyle(
        fontFamily = HsLinkSemiBoldFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.03).em
    ),

    title_16Strong = TextStyle(
        fontFamily = HsLinkSemiBoldFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.02).em
    ),
    title_14Strong = TextStyle(
        fontFamily = HsLinkMediumFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.02).em
    ),

    body_16Strong = TextStyle(
        fontFamily = HsLinkMediumFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.02).em
    ),
    body_16Normal = TextStyle(
        fontFamily = HsLinkRegularFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.02).em
    ),
    body_14Normal = TextStyle(
        fontFamily = HsLinkRegularFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.02).em
    ),

    caption_14Normal = TextStyle(
        fontFamily = HsLinkRegularFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.02).em
    ),
    caption_12Normal = TextStyle(
        fontFamily = HsLinkRegularFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.02).em
    ),

    btm_L = TextStyle(
        fontFamily = HsLinkSemiBoldFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.02).em
    ),
    btm_M = TextStyle(
        fontFamily = HsLinkSemiBoldFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.02).em
    ),
    btm_S = TextStyle(
        fontFamily = HsLinkSemiBoldFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.02).em
    )
)

val LocalHsLinkTypography = staticCompositionLocalOf { defaultHsLinkTypography }