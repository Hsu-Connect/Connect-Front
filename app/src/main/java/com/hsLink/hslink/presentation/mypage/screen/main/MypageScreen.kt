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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.data.dto.response.mypage.MyPageUserProfileDto
import com.hsLink.hslink.data.dto.response.mypage.MyPageUserSummaryDto
import com.hsLink.hslink.data.dto.response.mypage.UserProfileDto
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
    MypageScreen(paddingValues = PaddingValues())
}

@Composable
fun MypageRoute(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: MypageViewModel = hiltViewModel()
) {
    val userSummary by viewModel.userSummary.collectAsState() // ← 변경
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    MypageScreen(
        paddingValues = paddingValues,
        userSummary = userSummary, // ← 변경
        isLoading = isLoading,
        error = error,
        onNavigateToProfile = {
            navController.navigateToProfileEdit()
        }
    )
}

@Composable
fun MypageScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    userSummary: MyPageUserSummaryDto? = null, // ← 변경
    isLoading: Boolean = false,
    error: String? = null,
    onNavigateToProfile: () -> Unit = {},
    onNavigateToPosts: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onLogout: () -> Unit = {},
    onQuit: () -> Unit = {},
) {
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
                    // 클릭 처리
                }
            )
        }
    }
    }