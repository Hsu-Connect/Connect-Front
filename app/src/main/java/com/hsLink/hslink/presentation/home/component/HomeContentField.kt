package com.hsLink.hslink.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Composable
fun HomeContentField(
    mainText: String,
    subText: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = mainText,
            color = HsLinkTheme.colors.DeepBlue500,
            style = HsLinkTheme.typography.title_14Strong
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = subText,
                color = HsLinkTheme.colors.Grey700,
                style = HsLinkTheme.typography.title_20Strong
            )

            Spacer(modifier = Modifier.weight(1F))

            Text(
                text = stringResource(id = R.string.home_content_field_all),
                color = HsLinkTheme.colors.DeepBlue500,
                style = HsLinkTheme.typography.btm_M
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_textall_arrow),
                contentDescription = null,
                tint = HsLinkTheme.colors.DeepBlue500,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentFieldPreview() {
    HsLinkTheme {
        HomeContentField(
            mainText = "Main Text",
            subText = "Sub Text",
        )
    }
}