package com.hsLink.hslink.presentation.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme
import com.hsLink.hslink.domain.model.search.UserProfileEntity

@Preview(showBackground = true)
@Composable
private fun ProfileCardPreview() {
    HsLinkTheme {
        ProfileCard(
            profile = UserProfileEntity(
                userId = 1L,
                name = "송효재",
                studentNumberPrefix = "21",
                major = "회계재무경영",
                email = "test@test.com",
                jobSeeking = true,
                employed = true,
                academicStatus = "GRADUATED",
                careers = emptyList(),
                links = emptyList()
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileCardPreviewNotEmployed() {
    HsLinkTheme {
        ProfileCard(
            profile = UserProfileEntity(
                userId = 2L,
                name = "김민수",
                studentNumberPrefix = "20",
                major = "컴퓨터공학과",
                email = "test2@test.com",
                jobSeeking = false,
                employed = false,
                academicStatus = "ENROLLED",
                careers = emptyList(),
                links = emptyList()
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun StatusItem(
    title: String,
    subtitle: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = HsLinkTheme.typography.title_16Strong,
            color = HsLinkTheme.colors.Grey700
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subtitle,
            style = HsLinkTheme.typography.body_14Normal,
            color = HsLinkTheme.colors.Grey500
        )
    }
}

@Composable
fun ProfileCard(
    profile: UserProfileEntity,
    modifier: Modifier = Modifier
) {
    Box( // 전체 감싸기
        modifier = modifier
            .fillMaxWidth()
            .background(HsLinkTheme.colors.SkyBlue100), // 전체 폭 배경
        contentAlignment = Alignment.TopCenter
    ) {
        // 파란 배경
        Column(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .background(
                    color = HsLinkTheme.colors.SkyBlue100,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            // 상단 정보
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = profile.name,
                    style = HsLinkTheme.typography.title_24Strong,
                    color = HsLinkTheme.colors.Grey700
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${profile.studentNumberPrefix}학번",
                    style = HsLinkTheme.typography.body_14Normal,
                    color = HsLinkTheme.colors.Grey500
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = profile.major,
                style = HsLinkTheme.typography.body_16Normal,
                color = HsLinkTheme.colors.Grey700
            )

            Spacer(modifier = Modifier.height(40.dp)) // 하단 카드 걸칠 공간 확보
        }

        // 흰색 하단 박스 (겹치게 배치)
        androidx.compose.material3.Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 20.dp), // 파란 배경 아래로 20dp 걸치기
            shape = RoundedCornerShape(12.dp),
            color = HsLinkTheme.colors.Common,
            shadowElevation = 6.dp // 그림자 효과
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatusItem(
                    title = if (profile.jobSeeking) "구직 중" else "구직 안함",
                    subtitle = "구직 여부"
                )
                VerticalDivider(
                    modifier = Modifier.height(40.dp),
                    color = HsLinkTheme.colors.Grey200
                )
                StatusItem(
                    title = if (profile.employed) "재직 중" else "미재직",
                    subtitle = "재직 여부"
                )
                VerticalDivider(
                    modifier = Modifier.height(40.dp),
                    color = HsLinkTheme.colors.Grey200
                )
                StatusItem(
                    title = getAcademicStatusText(profile.academicStatus),
                    subtitle = "재학 여부"
                )
            }
        }
    }
}


private fun getAcademicStatusText(status: String): String {
    return when (status) {
        "ENROLLED" -> "재학"
        "GRADUATED" -> "졸업"
        "ON_LEAVE" -> "휴학"
        else -> "기타"
    }
}
