package com.hsLink.hslink.presentation.mypage.navigation.profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.Route
import com.hsLink.hslink.presentation.mypage.screen.profile.ProfileEditScreenRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToProfileEdit(
    navOptions: NavOptions? = null
) {
    navigate(ProfileEdit, navOptions)
}

fun NavGraphBuilder.profileEditNavGraph(
    padding: PaddingValues,
    navigateUp: () -> Unit,
) {
    composable<ProfileEdit> {
        ProfileEditScreenRoute(
            paddingValues = padding,
            onBackClick = navigateUp,
            onCloseClick = navigateUp
        )
    }
}

@Serializable
data object ProfileEdit : Route