package com.hsLink.hslink.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable


enum class HsLinkButtonSize(
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
) {
    Small(horizontalPadding = 16.dp, verticalPadding = 8.dp),
    Medium(horizontalPadding = 16.dp, verticalPadding = 9.dp),
    Large(horizontalPadding = 16.dp, verticalPadding = 12.dp);
    @Composable
    fun getTextStyle(): TextStyle = when (this) {
        Small -> HsLinkTheme.typography.btm_S
        Medium -> HsLinkTheme.typography.btm_M
        Large -> HsLinkTheme.typography.btm_L
    }
}

@Composable
fun HsLinkSelectButton(
    label: String,
    onClick: () -> Unit,
    size: HsLinkButtonSize,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isSelected: Boolean = false,
) {
    val textStyle = size.getTextStyle()

    val borderColor = when {
        !isEnabled -> HsLinkTheme.colors.Grey200
        isSelected -> HsLinkTheme.colors.SkyBlue500
        else -> HsLinkTheme.colors.SkyBlue500
    }

    val textColor = when {
        !isEnabled -> HsLinkTheme.colors.Grey200
        isSelected -> HsLinkTheme.colors.Common
        else -> HsLinkTheme.colors.SkyBlue500
    }

    val backgroundColor = when {
        !isEnabled -> HsLinkTheme.colors.Common
        isSelected -> HsLinkTheme.colors.SkyBlue500
        else -> HsLinkTheme.colors.Common
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .noRippleClickable(
                onClick = onClick,
                enabled = isEnabled,
            )
            .padding(
                horizontal = size.horizontalPadding,
                vertical = size.verticalPadding
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = textColor,
            style = textStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HsLinkSelectButtonPreview() {
    HsLinkTheme {
        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
        ){
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                HsLinkSelectButton(
                    label = "Button",
                    onClick = {},
                    size = HsLinkButtonSize.Large,
                    modifier = Modifier,
                    isEnabled = true,
                    isSelected = false
                )
                HsLinkSelectButton(
                    label = "Button",
                    onClick = {},
                    size = HsLinkButtonSize.Large,
                    modifier = Modifier,
                    isEnabled = true,
                    isSelected = true
                )

                HsLinkSelectButton(
                    label = "Button",
                    onClick = {},
                    size = HsLinkButtonSize.Large,
                    modifier = Modifier,
                    isEnabled = false,
                    isSelected = false
                )
            }
            Row (horizontalArrangement = Arrangement.spacedBy(8.dp)){
                HsLinkSelectButton(
                    label = "Button",
                    onClick = {},
                    size = HsLinkButtonSize.Medium,
                    modifier = Modifier,
                    isEnabled = true,
                    isSelected = false
                )
                HsLinkSelectButton(
                    label = "Button",
                    onClick = {},
                    size = HsLinkButtonSize.Medium,
                    modifier = Modifier,
                    isEnabled = true,
                    isSelected = true
                )
                HsLinkSelectButton(
                    label = "Button",
                    onClick = {},
                    size = HsLinkButtonSize.Medium,
                    modifier = Modifier,
                    isEnabled = false,
                    isSelected = false
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                HsLinkSelectButton(
                    label = "Button",
                    onClick = {},
                    size = HsLinkButtonSize.Small,
                    modifier = Modifier,
                    isEnabled = true,
                    isSelected = false
                )
                HsLinkSelectButton(
                    label = "Button",
                    onClick = {},
                    size = HsLinkButtonSize.Small,
                    modifier = Modifier,
                    isEnabled = true,
                    isSelected = true
                )
                HsLinkSelectButton(
                    label = "Button",
                    onClick = {},
                    size = HsLinkButtonSize.Small,
                    modifier = Modifier,
                    isEnabled = false,
                    isSelected = false
                )
            }
        }
    }
}