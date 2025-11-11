// HsLinkIconActionButton.kt (새로 생성)
package com.hsLink.hslink.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable

@Composable
fun HsLinkIconActionButton(
    label: String,
    @DrawableRes iconRes: Int,
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = textColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                color = textColor,
                style = textStyle
            )
        }
    }
}