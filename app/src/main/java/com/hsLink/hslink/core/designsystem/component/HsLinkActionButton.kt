package com.hsLink.hslink.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable

enum class HsLinkActionButtonSize(
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
) {
    Small(horizontalPadding = 48.dp, verticalPadding = 12.dp),
    Medium(horizontalPadding = 48.dp, verticalPadding = 9.dp),
    Large(horizontalPadding = 48.dp, verticalPadding = 12.dp);

    @Composable
    fun getTextStyle(): TextStyle = when (this) {
        Small -> HsLinkTheme.typography.btm_L
        Medium -> HsLinkTheme.typography.btm_M
        Large -> HsLinkTheme.typography.btm_L
    }
}

@Composable
fun HsLinkActionButton(
    label: String,
    onClick: () -> Unit,
    size: HsLinkActionButtonSize,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    val textStyle = size.getTextStyle()

    val textColor: Color
    val backgroundColor: Color

    if (isEnabled) {
        textColor = when(size){
            HsLinkActionButtonSize.Large -> HsLinkTheme.colors.Common
            HsLinkActionButtonSize.Medium -> HsLinkTheme.colors.Common
            HsLinkActionButtonSize.Small -> HsLinkTheme.colors.Grey500
          }
        backgroundColor = when (size) {
            HsLinkActionButtonSize.Large -> HsLinkTheme.colors.DeepBlue500
            HsLinkActionButtonSize.Medium -> HsLinkTheme.colors.SkyBlue400
            HsLinkActionButtonSize.Small -> HsLinkTheme.colors.Grey100
        }
    } else {
        textColor = when (size) {
            HsLinkActionButtonSize.Large -> HsLinkTheme.colors.Common
            HsLinkActionButtonSize.Medium -> HsLinkTheme.colors.Common
            HsLinkActionButtonSize.Small -> HsLinkTheme.colors.Grey500
        }
        backgroundColor = HsLinkTheme.colors.Grey200
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
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
private fun HsLinkActionButtonPreview() {
    HsLinkTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            HsLinkActionButton(label = "Small", onClick = {}, size = HsLinkActionButtonSize.Small)
            HsLinkActionButton(
                label = "Small",
                onClick = {},
                size = HsLinkActionButtonSize.Small,
                isEnabled = false
            )
            HsLinkActionButton(label = "Medium", onClick = {}, size = HsLinkActionButtonSize.Medium)
            HsLinkActionButton(
                label = "Medium",
                onClick = {},
                size = HsLinkActionButtonSize.Medium,
                isEnabled = false
            )
            HsLinkActionButton(label = "Large", onClick = {}, size = HsLinkActionButtonSize.Large)
            HsLinkActionButton(
                label = "Large",
                onClick = {},
                size = HsLinkActionButtonSize.Large,
                isEnabled = false
            )
        }
    }
}
