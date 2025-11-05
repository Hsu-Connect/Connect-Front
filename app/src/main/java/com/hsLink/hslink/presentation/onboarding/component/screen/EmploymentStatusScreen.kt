package com.hsLink.hslink.presentation.onboarding.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.component.HsLinkButtonSize
import com.hsLink.hslink.core.designsystem.component.HsLinkSelectButton
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen
import com.hsLink.hslink.presentation.onboarding.model.EmploymentStatus

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmploymentStatusScreen(
    selectedStatus: EmploymentStatus?,
    progress: Float,
    paddingValues: PaddingValues,
    onStatusSelect: (EmploymentStatus) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    OnboardingScreen(
        title = buildAnnotatedString {
            append("현재 재직 중이신가요? ")
            withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                append("*")
            }
        },
        progress = progress,
        subtitle = "자세한 알아주시면 더 맞은 도움을 드릴 수 있어요",
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = selectedStatus != null,
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            EmploymentStatus.entries.chunked(2).forEach { rowItems ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    rowItems.forEach { status ->
                        HsLinkSelectButton(
                            modifier = Modifier.weight(1f),
                            label = status.label,
                            onClick = { onStatusSelect(status) },
                            size = HsLinkButtonSize.Large,
                            isEnabled = true,
                            isSelected = selectedStatus == status
                        )
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmploymentStatusScreenPreview() {
    HsLinkTheme {
        EmploymentStatusScreen(
            selectedStatus = EmploymentStatus.WORKING,
            progress = 0.9f,
            paddingValues = PaddingValues(),
            onStatusSelect = {},
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}
