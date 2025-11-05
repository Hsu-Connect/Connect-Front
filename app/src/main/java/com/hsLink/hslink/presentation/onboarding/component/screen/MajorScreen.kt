package com.hsLink.hslink.presentation.onboarding.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.component.HsLinkTextField
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable
import com.hsLink.hslink.presentation.onboarding.OnboardingScreen
import com.hsLink.hslink.presentation.onboarding.model.MajorData

@Composable
fun MajorScreen(
    major: String,
    majorQuery: String,
    progress: Float,
    paddingValues: PaddingValues,
    onMajorQueryChange: (String) -> Unit,
    onMajorSelect: (String) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }


    val filteredMajors = remember(majorQuery) {
        if (majorQuery.isEmpty()) {
            emptyList()
        } else {
            MajorData.majors.filter { it.contains(majorQuery, ignoreCase = true) }
        }
    }

    val displayText = if (major.isNotEmpty()) major else majorQuery

    OnboardingScreen(
        title = buildAnnotatedString {
            append("전공을 선택해주세요 ")
            withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                append("*")
            }
        },
        progress = progress,
        paddingValues = paddingValues,
        showPreviousButton = true,
        nextButtonEnabled = major.isNotEmpty(),
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("전공선택 ")
                    withStyle(style = SpanStyle(color = HsLinkTheme.colors.Red500)) {
                        append("*")
                    }
                },
                style = HsLinkTheme.typography.body_14Normal,
                color = HsLinkTheme.colors.Grey700
            )

            Box {
                HsLinkTextField(
                    value = displayText,
                    placeholder = "전공을 선택해주세요",
                    onValueChanged = { newValue ->
                        if (major.isNotEmpty()) {
                            onMajorSelect("")
                        }
                        onMajorQueryChange(newValue)
                    },
                    borderColor = if (isFocused) HsLinkTheme.colors.DeepBlue500
                    else HsLinkTheme.colors.Grey300,
                    backgroundColor = HsLinkTheme.colors.Common,
                    onFocusChanged = { isFocused = it },
                    modifier = Modifier.fillMaxWidth(),
                    imeAction = ImeAction.Done,
                    leadingIconRes = if (major.isEmpty()) R.drawable.ic_onboarding_search else null
                )

                if (major.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 12.dp)
                            .noRippleClickable {
                                onMajorSelect("")
                                onMajorQueryChange("")
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "✕",
                            style = HsLinkTheme.typography.body_16Normal,
                            color = HsLinkTheme.colors.Grey400
                        )
                    }
                }
            }

            if (majorQuery.isNotEmpty() && major.isEmpty() && isFocused) {
                MajorDropdown(
                    majors = filteredMajors,
                    query = majorQuery,
                    onMajorSelect = { selectedMajor ->
                        onMajorSelect(selectedMajor)
                        onMajorQueryChange(selectedMajor)
                    }
                )
            }
        }
    }
}

@Composable
private fun MajorDropdown(
    majors: List<String>,
    query: String,
    onMajorSelect: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, HsLinkTheme.colors.Grey300, RoundedCornerShape(8.dp))
            .background(HsLinkTheme.colors.Common)
    ) {
        LazyColumn {
            items(majors) { major ->
                MajorItem(
                    major = major,
                    onClick = { onMajorSelect(major) }
                )
            }

            item {
                MajorItem(
                    major = "'$query' 직접 입력하기",
                    onClick = { onMajorSelect(query) },
                    isDirectInput = true
                )
            }
        }
    }
}

@Composable
private fun MajorItem(
    major: String,
    onClick: () -> Unit,
    isDirectInput: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = major,
            style = HsLinkTheme.typography.body_14Normal,
            color = if (isDirectInput) HsLinkTheme.colors.SkyBlue500
            else HsLinkTheme.colors.Grey700
        )
    }

    if (!isDirectInput) {
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = HsLinkTheme.colors.Grey200
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MajorScreenPreview() {
    HsLinkTheme {
        MajorScreen(
            major = "컴퓨터공학과",
            majorQuery = "컴퓨터공학과",
            progress = 0.4f,
            paddingValues = PaddingValues(),
            onMajorQueryChange = {},
            onMajorSelect = {},
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}
