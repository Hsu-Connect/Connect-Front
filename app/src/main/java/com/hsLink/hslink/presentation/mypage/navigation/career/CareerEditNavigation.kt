package com.hsLink.hslink.presentation.mypage.navigation.career

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hsLink.hslink.core.navigation.Route
import com.hsLink.hslink.presentation.mypage.screen.career.CareerEditRoute
import kotlinx.serialization.Serializable

// ← careerId 파라미터 추가
fun NavController.navigateToCareerEdit(careerId: Long, navOptions: NavOptions? = null) {
    navigate(CareerEdit(careerId = careerId), navOptions)
}

fun NavGraphBuilder.careerNavGraph(
    padding: PaddingValues,
    navController: NavController,
) {
    composable<CareerEdit> { backStackEntry ->
        val careerEdit = backStackEntry.arguments?.let {
            // ← careerId 추출
            CareerEdit(careerId = it.getLong("careerId"))
        } ?: CareerEdit(careerId = 0L)

        CareerEditRoute(
            paddingValues = padding,
            navController = navController,
            careerId = careerEdit.careerId // ← careerId 전달
        )
    }
}

// ← data object에서 data class로 변경
@Serializable
data class CareerEdit(val careerId: Long) : Route