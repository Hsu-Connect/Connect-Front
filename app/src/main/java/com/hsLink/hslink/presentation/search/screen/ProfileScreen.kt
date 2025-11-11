package com.hsLink.hslink.presentation.search.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButton
import com.hsLink.hslink.core.designsystem.component.HsLinkActionButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkIconActionButton
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.domain.model.search.CareerEntity
import com.hsLink.hslink.domain.model.search.LinkEntity
import com.hsLink.hslink.domain.model.search.UserProfileEntity
import com.hsLink.hslink.presentation.search.component.CareerCard
import com.hsLink.hslink.presentation.search.component.LinkCard
import com.hsLink.hslink.presentation.search.component.ProfileCard
import com.hsLink.hslink.presentation.search.state.ProfileIntent
import com.hsLink.hslink.presentation.search.state.ProfileSideEffect
import com.hsLink.hslink.presentation.search.state.ProfileUiState
import com.hsLink.hslink.presentation.search.viewmodel.ProfileViewModel

@Composable
fun ProfileRoute(
    userId: Long,
    paddingValues: PaddingValues,
    onNavigateBack: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.handleIntent(ProfileIntent.LoadProfile(userId))
    }

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is ProfileSideEffect.NavigateBack -> {
                    onNavigateBack()
                }
                is ProfileSideEffect.ShowError -> {
                    // TODO: Toast 또는 SnackBar 처리
                }
            }
        }
    }

    ProfileScreen(
        paddingValues = paddingValues,
        uiState = uiState,
        onIntent = viewModel::handleIntent
    )
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    uiState: ProfileUiState,
    onIntent: (ProfileIntent) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = HsLinkTheme.colors.Common)
            .padding(paddingValues),
    ) {
        item {
            HsLinkTopBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {
                    Text(
                        text = "한성인 찾기",
                        style = HsLinkTheme.typography.title_20Strong,
                        color = HsLinkTheme.colors.Grey600
                    )
                },
                leftIcon = R.drawable.ic_topbar_arrowleft,
                onLeftIconClick = { onIntent(ProfileIntent.NavigateBack) },
            )
        }

        if (uiState.isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        uiState.userProfile?.let { profile ->
            // 프로필 헤더 추가
            item {
                Spacer(modifier = Modifier.height(32.dp))
                ProfileCard(
                    profile = profile,
                    //modifier = Modifier.padding(16.dp)
                )
            }

            // 커리어 섹션 추가
            if (profile.careers.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                    CareerCard(
                        careers = profile.careers,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            // 링크 섹션 추가
            if (profile.links.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                    LinkCard(
                        links = profile.links,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            // 하단 버튼 추가
            item {
                Spacer(modifier = Modifier.height(32.dp))
                val context = LocalContext.current
                HsLinkIconActionButton(
                    label = "멘토링 메일 보내기",
                    iconRes = R.drawable.ic_search_mail,
                    onClick = {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:${profile.email}") // 프로필의 이메일 주소 사용
                            putExtra(Intent.EXTRA_SUBJECT, "멘토링 문의드립니다.")
                            putExtra(Intent.EXTRA_TEXT, "안녕하세요, ${profile.name}님.\n\n멘토링 관련하여 문의드립니다.")
                        }
                        context.startActivity(intent)
                    },
                    size = HsLinkActionButtonSize.Large,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 32.dp)
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    HsLinkTheme {
        ProfileScreen(
            paddingValues = PaddingValues(),
            uiState = ProfileUiState(
                userProfile = UserProfileEntity(
                    userId = 1L,
                    name = "송효재",
                    studentNumberPrefix = "21",
                    major = "회계재무경영",
                    email = "test@test.com",
                    jobSeeking = true,
                    employed = true,
                    academicStatus = "GRADUATED",
                    careers = listOf(
                        CareerEntity(
                            id = 1L,
                            companyName = "Sebp",
                            position = "컨설턴트 · 인턴",
                            jobType = "PERMANENT",
                            employed = true,
                            startYm = "25.08",
                            endYm = "재직 중"
                        ),
                        CareerEntity(
                            id = 2L,
                            companyName = "한국생산성본부",
                            position = "영업직 · 인턴",
                            jobType = "PERMANENT",
                            employed = false,
                            startYm = "25.01",
                            endYm = "25.06"
                        )
                    ),
                    links = listOf(
                        LinkEntity(
                            id = 1L,
                            type = "INSTAGRAM",
                            url = "https://www.instagram.com/02_sing_song/"
                        ),
                        LinkEntity(
                            id = 2L,
                            type = "BLOG",
                            url = "https://www.instagram.com/02_sing_song/"
                        )
                    )
                )
            ),
            onIntent = {}
        )
    }
}