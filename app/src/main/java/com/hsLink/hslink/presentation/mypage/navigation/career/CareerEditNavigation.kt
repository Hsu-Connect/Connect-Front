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
fun NavController.navigateToCareerEdit(careerId: Int, navOptions: NavOptions? = null) {
    navigate(CareerEdit(careerId = careerId), navOptions)
}

fun NavGraphBuilder.careerNavGraph(
    padding: PaddingValues,
    navController: NavController,
) {
    composable<CareerEdit> { backStackEntry ->
        val careerEdit = backStackEntry.arguments?.let {
            CareerEdit(careerId = it.getInt("careerId")) // ← getLong → getInt 변경!
        } ?: CareerEdit(careerId = 0) // ← 0L → 0 변경!

        CareerEditRoute(
            paddingValues = padding,
            navController = navController,
            careerId = careerEdit.careerId.toLong() // ← CareerEditRoute가 Long을 받으면 여기서 변환
        )
    }
}

@Serializable
data class CareerEdit(val careerId: Int) : Route