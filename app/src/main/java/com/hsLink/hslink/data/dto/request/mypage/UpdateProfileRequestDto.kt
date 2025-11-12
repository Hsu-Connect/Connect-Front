// data/dto/request/mypage/UpdateProfileRequestDto.kt
package com.hsLink.hslink.data.dto.request.mypage

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequestDto(
    val studentNumber: String? = null,
    val name: String? = null,
    val major: String? = null,
    val mentor: Boolean? = null,
    val jobSeeking: Boolean? = null
)