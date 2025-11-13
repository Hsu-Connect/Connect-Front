package com.hsLink.hslink.presentation.onboarding.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable
import com.hsLink.hslink.data.dto.response.onboarding.CareerDto
import com.hsLink.hslink.domain.model.search.CareerItemEntity
import com.hsLink.hslink.presentation.mypage.component.profile.CareerCard
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen

@Composable
fun CareerScreen(
    selectedCareer: Boolean?,
    //careerList: List<CareerItemEntity>,
    careerList: List<CareerDto>,
    progress: Float,
    paddingValues: PaddingValues,
    onCareerSelect: (Boolean) -> Unit,
    onCareerClick: (CareerDto) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    onAddCareerClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val onSkipPathClick = {
        onCareerSelect(false)
        onNextClick()
    }

    val onExperiencedPathClick = {
        onCareerSelect(true)
        onAddCareerClick()
    }

    OnboardingScreen(
        title = "지금까지의 커리어를 알려주세요",
        progress = progress,
        subtitle = "아직 경력이 없다면 '다음' 버튼으로 넘겨도 괜찮아요.",
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = true,
        onPreviousClick = onPreviousClick,
        onNextClick = onSkipPathClick
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (careerList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(careerList) { career ->
                            val dateRange = if (career.employed) {
                                "${career.startYm} ~ 재직 중"
                            } else {
                                "${career.startYm} ~ ${career.endYm ?: "-"}"
                            }

                            CareerCard(
                                name = career.companyName,
                                title = career.position,
                                dateRange = dateRange,
                                onClick = {
                                    onCareerClick(career)
                                }
                            )
                        }
                    }
                }
            }

            // ← "커리어 추가하기" 버튼이 여기에 와야 해요!
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = HsLinkTheme.colors.Common,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = HsLinkTheme.colors.Grey100,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(vertical = 40.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "경력을 등록하고 동문들과 정보를 나눠보세요!",
                        style = HsLinkTheme.typography.title_16Strong,
                        color = HsLinkTheme.colors.Grey700
                    )
                    Text(
                        text = "커리어를 등록하면 더 많은 정보를 얻을 수 있어요.",
                        style = HsLinkTheme.typography.body_14Normal,
                        color = HsLinkTheme.colors.Grey500
                    )
                    CareerSelectButton(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = onExperiencedPathClick,
                        label = "커리어 추가하기",
                        isEnabled = true
                    )
                }
            }
        } // ← 여기서 Column이 끝나야 해요!
    }
}

@Composable
private fun CareerItem(
    career: CareerItemEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = HsLinkTheme.colors.Grey100,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = career.companyName,
            style = HsLinkTheme.typography.title_16Strong,
            color = HsLinkTheme.colors.Grey700
        )
        Text(
            text = career.position,
            style = HsLinkTheme.typography.body_14Normal,
            color = HsLinkTheme.colors.Grey500
        )
        career.jobType?.let {
            Text(
                text = it.label,
                style = HsLinkTheme.typography.body_14Normal,
                color = HsLinkTheme.colors.Grey400
            )
        }
    }
}

@Composable
private fun CareerSelectButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    isEnabled: Boolean = true,
) {
    Row(
        modifier = modifier
            .background(
                color = HsLinkTheme.colors.Grey100,
                shape = RoundedCornerShape(8.dp)
            )
            .noRippleClickable(
                onClick = onClick,
                enabled = isEnabled,
            )
            .padding(horizontal = 68.dp, vertical = 9.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_onboarding_plus),
            contentDescription = "커리어 추가하기",
            tint = HsLinkTheme.colors.Grey500
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = label,
            color = HsLinkTheme.colors.Grey500,
            style = HsLinkTheme.typography.btm_M
        )
    }
}