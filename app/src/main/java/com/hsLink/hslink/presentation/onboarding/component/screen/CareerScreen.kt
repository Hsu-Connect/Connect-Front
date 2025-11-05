package com.hsLink.hslink.presentation.onboarding.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen
import com.hsLink.hslink.presentation.onboarding.model.EmploymentStatus

@Composable
fun CareerScreen(
    selectedStatus: EmploymentStatus?,
    progress: Float,
    paddingValues: PaddingValues,
    onStatusSelect: (EmploymentStatus) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OnboardingScreen(
        title = "지금까지의 커리어를 알려주세요",
        progress = progress,
        subtitle = "아직 경력이 없다면 넘겨도 괜찮아요",
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = true,
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = HsLinkTheme.colors.Common,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.dp,
                    color = HsLinkTheme.colors.Grey100,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "아직 등록된 커리어가 없어요",
                    style = HsLinkTheme.typography.title_16Strong,
                    color = HsLinkTheme.colors.Grey700
                )
                Text(
                    text = "더 많은 정보를 얻기 위해 커리어를 등록해요",
                    style = HsLinkTheme.typography.body_14Normal,
                    color = HsLinkTheme.colors.Grey500
                )
                CareerSelectButton(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = {},
                    label = "커리어 추가하기",
                    isEnabled = true
                )
            }
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
            .padding(
                horizontal = 68.dp,
                vertical = 9.dp
            ),
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

@Preview(showBackground = true)
@Composable
private fun ReviewCareerScreen() {
    HsLinkTheme {
        CareerScreen(
            selectedStatus = null,
            progress = 0.5f,
            paddingValues = PaddingValues(0.dp),
            onStatusSelect = {},
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}
