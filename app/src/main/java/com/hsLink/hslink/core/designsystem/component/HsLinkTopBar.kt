package com.hsLink.hslink.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun HsLinkTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    @DrawableRes rightIconFirst: Int? = null,
    @DrawableRes rightIconSecond: Int? = null,
    @DrawableRes leftIcon: Int? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        leftIcon?.let {
            Icon(
                imageVector = ImageVector.vectorResource(id = it),
                contentDescription = "leftIcon"
            )
        }

        title()

        Spacer(modifier = Modifier.weight(1f))

        rightIconFirst?.let {
            Icon(
                imageVector = ImageVector.vectorResource(id = it),
                contentDescription = "rightIconFirst"
            )
        }

        rightIconSecond?.let {
            Icon(
                imageVector = ImageVector.vectorResource(id = it),
                contentDescription = "rightIconSecond"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HsLinkTopBarPreview() {
    HsLinkTheme {
        HsLinkTopBar(
            title = { Text("Title") },
            rightIconFirst = R.drawable.ic_topbar_plus,
            rightIconSecond = R.drawable.ic_topbar_plus,
            leftIcon = R.drawable.ic_topbar_plus,
        )
    }
}