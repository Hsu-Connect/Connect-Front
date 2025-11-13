package com.hsLink.hslink.data.dto.request.onboarding

import com.hsLink.hslink.presentation.onboarding.model.JobType
import com.hsLink.hslink.presentation.onboarding.model.LinkType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CareerRequest(
    @SerialName("companyName")
    val companyName: String,
    @SerialName("position")
    val position: String,
    @SerialName("jobType")
    val jobType: JobType,
    @SerialName("startYm")
    val startYm: String,
    @SerialName("endYm")
    val endYm: String? = null,
    @SerialName("employed")
    val employed: Boolean
)

@Serializable
data class LinkRequest(
    @SerialName("type")
    val type: LinkType,
    @SerialName("url")
    val url: String
)
@Serializable
data class CareerUpdateRequestDto(
    val companyName: String,
    val position: String,
    val jobType: JobType,
    val startYm: String,
    val endYm: String?,
    val employed: Boolean
)

