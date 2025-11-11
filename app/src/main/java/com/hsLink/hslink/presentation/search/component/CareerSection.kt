package com.hsLink.hslink.presentation.search.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.domain.model.search.CareerEntity

@Composable
fun CareerCard(
    careers: List<CareerEntity>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "커리어",
            style = HsLinkTheme.typography.title_16Strong,
            color = HsLinkTheme.colors.Grey700,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = HsLinkTheme.colors.Common
            ),
            border = BorderStroke(
                width = 1.dp,
                color = HsLinkTheme.colors.Grey200
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                careers.forEachIndexed { index, career ->
                    CareerItem(career = career)
                    if (index < careers.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = HsLinkTheme.colors.Grey200
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CareerItem(
    career: CareerEntity,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = career.companyName,
            style = HsLinkTheme.typography.body_16Strong,
            color = HsLinkTheme.colors.Grey700
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = career.position,
            style = HsLinkTheme.typography.body_16Strong,
            color = HsLinkTheme.colors.Grey700
        )

        Spacer(modifier = Modifier.height(4.dp))

        val period = if (career.endYm != null) {
            "${career.startYm} - ${career.endYm}"
        } else {
            "${career.startYm} - 현재"
        }

        Text(
            text = period,
            style = HsLinkTheme.typography.body_16Strong,
            color = HsLinkTheme.colors.Grey700
        )
    }
}