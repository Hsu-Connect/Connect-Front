package com.hsLink.hslink.presentation.search.screen

import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.domain.model.search.CareerEntity
import com.hsLink.hslink.domain.model.search.LinkEntity
import com.hsLink.hslink.domain.model.search.UserProfileEntity
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
                rightIconFirst = R.drawable.ic_topbar_close,
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
            item {
                ProfileHeader(
                    profile = profile,
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (profile.careers.isNotEmpty()) {
                item {
                    CareerSection(
                        careers = profile.careers,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            if (profile.links.isNotEmpty()) {
                item {
                    LinkSection(
                        links = profile.links,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileHeader(
    profile: UserProfileEntity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HsLinkTheme.colors.Common)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = profile.name,
                style = HsLinkTheme.typography.title_24Strong,
                color = HsLinkTheme.colors.Grey600
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${profile.studentNumberPrefix}학번 · ${profile.major}",
                style = HsLinkTheme.typography.body_16Strong,
                color = HsLinkTheme.colors.Grey700
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                if (profile.jobSeeking) {
                    StatusChip(text = "구직 중")
                    Spacer(modifier = Modifier.width(8.dp))
                }
                if (profile.employed) {
                    StatusChip(text = "재직 중")
                    Spacer(modifier = Modifier.width(8.dp))
                }
                StatusChip(text = getAcademicStatusText(profile.academicStatus))
            }
        }
    }
}

@Composable
private fun StatusChip(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = HsLinkTheme.colors.Grey100)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = HsLinkTheme.typography.body_16Normal,
            color = HsLinkTheme.colors.Grey700
        )
    }
}

@Composable
private fun CareerSection(
    careers: List<CareerEntity>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "커리어",
            style = HsLinkTheme.typography.title_16Strong,
            color = HsLinkTheme.colors.Grey700,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        careers.forEachIndexed { index, career ->
            CareerItem(career = career)
            if (index < careers.size - 1) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = HsLinkTheme.colors.Grey200
                )
            }
        }
    }
}

@Composable
private fun CareerItem(
    career: CareerEntity,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = career.companyName,
            style = HsLinkTheme.typography.body_16Strong,
            color = HsLinkTheme.colors.Grey700
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = career.position,
            style = HsLinkTheme.typography.body_16Strong,
            color = HsLinkTheme.colors.Grey700
        )

        Spacer(modifier = Modifier.height(4.dp))

        val period = if (career.endYm != null) {
            "${career.startYm} - ${career.endYm}"
        } else {
            "${career.startYm} - 현재"
        }

        Text(
            text = period,
            style = HsLinkTheme.typography.body_16Strong,
            color = HsLinkTheme.colors.Grey700
        )
    }
}

@Composable
private fun LinkSection(
    links: List<LinkEntity>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "자기소개 링크",
            style = HsLinkTheme.typography.body_16Strong,
            color = HsLinkTheme.colors.Grey700,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        links.forEach { link ->
            LinkItem(link = link)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun LinkItem(
    link: LinkEntity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = HsLinkTheme.colors.Grey700)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = getLinkTypeText(link.type),
                style = HsLinkTheme.typography.body_16Strong,
                color = HsLinkTheme.colors.Grey700
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = link.url,
                style = HsLinkTheme.typography.body_16Strong,
                color = HsLinkTheme.colors.DeepBlue500
            )
        }
    }
}

private fun getAcademicStatusText(status: String): String {
    return when (status) {
        "ENROLLED" -> "재학"
        "GRADUATED" -> "졸업"
        "ON_LEAVE" -> "휴학"
        else -> "기타"
    }
}

private fun getLinkTypeText(type: String): String {
    return when (type) {
        "LINKEDIN" -> "LinkedIn"
        "GITHUB" -> "GitHub"
        "INSTAGRAM" -> "Instagram"
        "BLOG" -> "블로그"
        else -> "기타"
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(
        paddingValues = PaddingValues(),
        uiState = ProfileUiState(
            userProfile = UserProfileEntity(
                userId = 1L,
                name = "홍길동",
                studentNumberPrefix = "20",
                major = "컴퓨터공학과",
                email = "test@test.com",
                jobSeeking = true,
                employed = false,
                academicStatus = "ENROLLED",
                careers = listOf(
                    CareerEntity(
                        id = 1L,
                        companyName = "네이버",
                        position = "개발자",
                        jobType = "PERMANENT",
                        employed = true,
                        startYm = "2023.03",
                        endYm = null
                    )
                ),
                links = listOf(
                    LinkEntity(
                        id = 1L,
                        type = "GITHUB",
                        url = "https://github.com/test"
                    )
                )
            )
        ),
        onIntent = {}
    )
}