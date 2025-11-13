package com.hsLink.hslink.presentation.mypage.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.data.dto.response.mypage.MyPageUserProfileDto
import com.hsLink.hslink.data.dto.response.mypage.MyPageUserSummaryDto
import com.hsLink.hslink.data.dto.response.mypage.UserProfileDto
import com.hsLink.hslink.presentation.login.navigation.navigateToLogin
import com.hsLink.hslink.presentation.mypage.component.career.ConfirmDialog
import com.hsLink.hslink.presentation.mypage.component.main.MyPageCardItemContainer
import com.hsLink.hslink.presentation.mypage.component.main.MyPageDetailItemContent
import com.hsLink.hslink.presentation.mypage.component.main.MyPageItemData
import com.hsLink.hslink.presentation.mypage.navigation.profile.navigateToProfileEdit
import com.hsLink.hslink.presentation.mypage.viewmodel.MypageViewModel

// 상태 텍스트 만드는 함수
private fun buildStatusText(jobSeeking: Boolean, academicStatus: String, employed: Boolean): String {
    val jobText = if (jobSeeking) "구직 중" else "구직 중 아님"
    val employedText = if (employed) "재직 중" else "재직 중 아님"
    val academicText = when (academicStatus) {
        "ENROLLED" -> "재학중"
        "GRADUATED" -> "졸업"
        "EXPECTED_GRADUATION" -> "졸업예정"
        "COMPLETED" -> "수료"
        "LEAVE_OF_ABSENCE" -> "휴학"
        else -> academicStatus
    }
    return "$jobText · $employedText · $academicText"
}

@Preview(showBackground = true)
@Composable
private fun MypageScreenPreview() {
}

@Composable
fun MypageRoute(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: MypageViewModel = hiltViewModel()
) {
    val userSummary by viewModel.userSummary.collectAsState() // ← 변경
    val isLoading by viewModel.isLoading.collectAsState()
    val isAuthLoading by viewModel.isAuthLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val logoutSuccess by viewModel.logoutSuccess.collectAsState() // ← 추가
    val withdrawSuccess by viewModel.withdrawSuccess.collectAsState() // ← 추가



    LaunchedEffect(Unit) {
        viewModel.loadUserSummary() // 또는 loadMypage()
    }

    // ← 프로필 수정 후 돌아왔을 때 새로고침
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry) {
        // 프로필 수정 화면에서 돌아왔을 때만 새로고침
        if (currentBackStackEntry?.destination?.route?.contains("mypage") == true) {
            viewModel.loadUserSummary()
        }
    }

    // 로그아웃/탈퇴 성공 시 처리
    LaunchedEffect(error) {
        error?.let { errorMessage ->
            if (errorMessage.contains("성공")) {
                // 로그인 화면으로 이동 (추후 구현)
                // navController.navigateToLogin()
            }
        }
    }


    LaunchedEffect(logoutSuccess, withdrawSuccess) {
        if (logoutSuccess || withdrawSuccess) {
            navController.navigateToLogin(
                navOptions = NavOptions.Builder()
                    .setPopUpTo(0, inclusive = true) // ← 모든 백스택 클리어
                    .build()
            )
        }
    }
    MypageScreen(
        paddingValues = paddingValues,
        userSummary = userSummary,
        isLoading = isLoading,
        isAuthLoading = isAuthLoading, // ← 추가
        error = error,
        onNavigateToProfile = {
            navController.navigateToProfileEdit()
        },
        onLogout = viewModel::logout, // ← 추가
        onWithdraw = viewModel::withdraw // ← 추가
    )
}

@Composable
fun MypageScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    userSummary: MyPageUserSummaryDto? = null, // ← 변경
    isLoading: Boolean = false,
    error: String? = null,
    isAuthLoading: Boolean = false, // ← 추가
    onNavigateToProfile: () -> Unit = {},
    onNavigateToPosts: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onLogout: () -> Unit = {}, // ← 추가
    onWithdraw: () -> Unit = {}, // ← 추가
) {

    // ← 다이얼로그 상태 관리
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showWithdrawDialog by remember { mutableStateOf(false) }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = HsLinkTheme.colors.Common)
            .padding(paddingValues),
    ) {
        item {
            HsLinkTopBar(
                modifier = Modifier
                    .padding(start = 12.dp),
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.img_home_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .height(48.dp)
                            .width(92.dp),
                        contentScale = ContentScale.FillBounds,
                    )
                },
                rightIconFirst = null,
                rightIconSecond = null,
                leftIcon = null
            )
        }

        item {
            MyPageDetailItemContent(
                name = userSummary?.name ?: "로딩중...", // ← 변경
                title = if (userSummary != null) {
                    "${userSummary.studentNumberPrefix}학번 ${userSummary.major}" // ← 변경
                } else "로딩중...",
                subtitle = if (userSummary != null) {
                    buildStatusText(userSummary.jobSeeking, userSummary.academicStatus, userSummary.employed) // ← employed 추가
                } else "로딩중...",
                onClick = onNavigateToProfile
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            MyPageCardItemContainer(
                text = "계정",
                items = listOf(
                    MyPageItemData(id = "3", title = "로그아웃", route = "/logout"),
                    MyPageItemData(id = "4", title = "탈퇴하기", route = "/quit")
                ),
                onItemClick = { item ->
                    when (item.id) {
                        "3" -> showLogoutDialog = true  // ← 로그아웃 다이얼로그 표시
                        "4" -> showWithdrawDialog = true // ← 탈퇴 다이얼로그 표시
                    }
                }
            )
        }
    }

    // ← 로그아웃 확인 다이얼로그
    if (showLogoutDialog) {
        ConfirmDialog(
            title = "로그아웃을\n하시겠습니까?",
            message = null,
            cancelText = "취소하기",
            confirmText = "로그아웃",
            onDismiss = { showLogoutDialog = false },
            onConfirm = {
                showLogoutDialog = false
                onLogout() // ← 실제 로그아웃 실행
            }
        )
    }

    // ← 계정 삭제 확인 다이얼로그
    if (showWithdrawDialog) {
        ConfirmDialog(
            title = "계정을 삭제하시겠습니까?",
            message = "데이터가 복구되지 않는데 괜찮으신가요?",
            cancelText = "취소하기",
            confirmText = "삭제하기",
            onDismiss = { showWithdrawDialog = false },
            onConfirm = {
                showWithdrawDialog = false
                onWithdraw() // ← 실제 계정 탈퇴 실행
            }
        )
    }
}