package com.hsLink.hslink.data.dto.response.mypage

import kotlinx.serialization.Serializable


@Serializable
data class MyPageUserSummaryDto(
    val userId: Long,
    val name: String,
    val studentNumberPrefix: String,  // 학번 앞 두 자리 (예: "21")
    val major: String,
    val jobSeeking: Boolean,
    val academicStatus: String,  // <- String으로 변경
    val employed: Boolean
)