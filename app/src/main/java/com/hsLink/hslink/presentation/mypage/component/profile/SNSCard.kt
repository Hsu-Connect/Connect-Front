package com.hsLink.hslink.presentation.mypage.component.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Composable
fun SNSCard(
    title: String,
    content: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = HsLinkTheme.colors.Common
        ),
        border = BorderStroke(
            width = 1.dp,
            color = HsLinkTheme.colors.Grey200
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    color = Black,
                    style = HsLinkTheme.typography.title_20Strong
                )

                Text(
                    text = content,
                    color = HsLinkTheme.colors.Grey400,
                    style = HsLinkTheme.typography.body_14Normal
                )
            }

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_mypage_item_lefrarrow),
                contentDescription = null,
                tint = Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SNSCardPreview() {
    HsLinkTheme {
        SNSCard(
            title = "SNS",
            content = "인스타그램",
            onClick = { }
        )
    }
}