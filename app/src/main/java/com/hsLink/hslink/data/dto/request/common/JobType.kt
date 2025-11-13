package com.hsLink.hslink.data.dto.request.common

import kotlinx.serialization.Serializable

// data/dto/common/JobType.kt
@Serializable
enum class JobType {
    PERMANENT,   // 정규직
    TEMPORARY,   // 계약직
    INTERN,      // 인턴
    FREELANCER   // 프리랜서
}