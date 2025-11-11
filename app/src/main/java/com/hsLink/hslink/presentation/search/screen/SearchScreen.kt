package com.hsLink.hslink.presentation.search.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkTopBar
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.search.component.SearchUserItems
import com.hsLink.hslink.presentation.search.state.SearchIntent
import com.hsLink.hslink.presentation.search.state.SearchSideEffect
import com.hsLink.hslink.presentation.search.state.SearchUiState
import com.hsLink.hslink.presentation.search.viewmodel.SearchViewModel

@Composable
@Preview(showBackground = true)
fun SearchScreenPreview() {
    SearchScreen(
        paddingValues = PaddingValues(),
        onNavigateToProfile = {}
    )
}

@Composable
fun SearchRoute(
    paddingValues: PaddingValues,
    onNavigateToProfile: (Long) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(SearchIntent.LoadMentors)
    }

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SearchSideEffect.NavigateToProfile -> {
                    onNavigateToProfile(sideEffect.userId)
                }
                is SearchSideEffect.ShowError -> {
                    // TODO: Toast 또는 SnackBar 처리
                }
            }
        }
    }

    SearchScreen(
        paddingValues = paddingValues,
        uiState = uiState,
        onIntent = viewModel::handleIntent,
        onNavigateToProfile = onNavigateToProfile
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    uiState: SearchUiState = SearchUiState(),
    onIntent: (SearchIntent) -> Unit = {},
    onNavigateToProfile: (Long) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = HsLinkTheme.colors.Common)
            .padding(paddingValues),
    ) {
        item {
            HsLinkTopBar(
                modifier = Modifier.padding(start = 12.dp),
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
            Column {
                Text(
                    text = "멘토링을 받고 싶은\n한성인을 찾아보세요",
                    color = HsLinkTheme.colors.DeepBlue500,
                    style = HsLinkTheme.typography.title_24Strong
                )
                Text("전체 ${uiState.totalMentorCount}명")
            }
        }

        if (uiState.isLoading && uiState.mentors.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        items(
            items = uiState.mentors,
            key = { it.userId }
        ) { mentor ->
            SearchUserItems(
                name = mentor.name,
                title = "${mentor.major}",
                subtitle = buildStatusText(mentor.jobSeeking, mentor.employed, mentor.academicStatus),
                onClick = { onIntent(SearchIntent.NavigateToProfile(mentor.userId)) }
            )
        }

        if (uiState.isLoadingMore) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

private fun buildStatusText(jobSeeking: Boolean, employed: Boolean, academicStatus: String): String {
    val status = mutableListOf<String>()
    if (jobSeeking) status.add("구직 중")
    if (employed) status.add("재직 중")
    status.add(getAcademicStatusText(academicStatus))
    return status.joinToString(" · ")
}

private fun getAcademicStatusText(status: String): String {
    return when (status) {
        "ENROLLED" -> "재학"
        "GRADUATED" -> "졸업"
        "ON_LEAVE" -> "휴학"
        else -> "기타"
    }
}