package com.hsLink.hslink.presentation.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable

@Preview(showBackground = true)
@Composable
private fun PreviewCommunityWriteButton() {
    HsLinkTheme {
        CommunityWriteButton(
            isDone = true,
            onClick = {}
        )
    }
}

@Composable
fun CommunityWriteButton(
    isDone: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backColor = when (isDone) {
        true -> HsLinkTheme.colors.DeepBlue500
        false -> HsLinkTheme.colors.Grey200
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backColor, shape = RoundedCornerShape(8.dp))
            .noRippleClickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_community_button),
            contentDescription = null,
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "글작성 마치기",
            color = HsLinkTheme.colors.Common,
            style = HsLinkTheme.typography.btm_L
        )

    }
}