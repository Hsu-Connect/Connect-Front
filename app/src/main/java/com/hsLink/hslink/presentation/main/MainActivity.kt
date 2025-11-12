package com.hsLink.hslink.presentation.main

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.login.screen.KaKaoLoginScreen
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import java.security.MessageDigest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 카카오 SDK 초기화
        KakaoSdk.init(this, "a0bbd28c9384baa131a731d1b914307c")

        // 키 해시 확인 (API 레벨에 따라 다른 방법 사용)
        try {
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            } else {
                @Suppress("DEPRECATION")
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            }

            val signatures = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.signingInfo?.apkContentsSigners
            } else {
                @Suppress("DEPRECATION")
                packageInfo.signatures
            }

            signatures?.let { sigs ->
                for (signature in sigs) {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    val keyHash = Base64.encodeToString(md.digest(), Base64.NO_WRAP)
                    Log.d("KeyHash", "KeyHash: $keyHash")
                    println("KeyHash: $keyHash")
                }
            }
        } catch (e: Exception) {
            Log.e("KeyHash", "Error getting key hash", e)
        }

        enableEdgeToEdge()
        setContent {
            HsLinkTheme {
                AppNavigation()
            }
        }
    }
}

@Serializable
data object AuthGraph

@Serializable
data object Login

@Serializable
data object Onboarding

@Serializable
data object AppMain


@Composable
private fun AppNavigation() {
    val navController = rememberNavController()
    var isLoginChecked by remember { mutableStateOf(false) }
    var isLoggedIn by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isLoggedIn = checkLoginStatus()
        isLoginChecked = true
    }

    Scaffold { paddingValues ->
        if (!isLoginChecked) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            NavHost(
                navController = navController,
                startDestination = if (isLoggedIn) AppMain else AuthGraph
            ) {
                authNavGraph(
                    navController = navController,
                    paddingValues = paddingValues,
                    onNavigateToOnboarding = {
                        navController.navigate(Onboarding)
                    },
                    onNavigateToAppMain = {
                        navController.navigate(AppMain) {
                            popUpTo(AuthGraph) { inclusive = true }
                        }
                    }
                )

                composable<AppMain> {
                    MainScreen()
                }
            }
        }
    }
}



fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToAppMain: () -> Unit,
) {
    navigation<AuthGraph>(startDestination = Login) {

        composable<Login> {
            KaKaoLoginScreen(
                paddingValues = paddingValues,
                onNavigateToHome = onNavigateToOnboarding,
                onNavigateToOnboarding = onNavigateToOnboarding
            )
        }

        composable<Onboarding> {
            com.hsLink.hslink.presentation.onboarding.OnboardingRoute(
                paddingValues = paddingValues,
                navigateUp = { navController.popBackStack() },
                navigateToHome = onNavigateToAppMain
            )
        }
    }
}


private suspend fun checkLoginStatus(): Boolean {
    return false
}