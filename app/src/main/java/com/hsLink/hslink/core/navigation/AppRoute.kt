package com.hsLink.hslink.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data object AppMain : Route  // 전체 앱의 메인 (기존 MainScreen)

@Serializable
data object Onboarding : Route