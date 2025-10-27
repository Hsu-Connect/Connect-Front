package com.hsLink.hslink.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.core.util.noRippleClickable

@Preview(showBackground = true)
@Composable
private fun HomeBottomContentPreview() {
    HsLinkTheme {
        HomeBottomContent(
            onClick = {},
        )
    }
}

@Composable
fun HomeBottomContent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_home_logo),
            contentDescription = null,
            modifier = Modifier
                .height(48.dp)
                .width(92.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "이용약관 및 개인정보 취급방침",
            color = HsLinkTheme.colors.Grey400,
            style = HsLinkTheme.typography.btm_M,
            modifier = Modifier.noRippleClickable(onClick = onClick)
        )
        Text(
            text = "리뷰운영정책",
            color = HsLinkTheme.colors.Grey400,
            style = HsLinkTheme.typography.btm_M,
            modifier = Modifier.noRippleClickable(onClick = onClick)
        )

    }
}