package com.hsLink.hslink.data.dto.response.onboarding

import com.hsLink.hslink.domain.model.search.CareerItemEntity
import com.hsLink.hslink.domain.model.search.LinkItemEntity
import com.hsLink.hslink.presentation.onboarding.model.JobType
import com.hsLink.hslink.presentation.onboarding.model.LinkType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CareerResponse(
    @SerialName("id") val id: Int,
    @SerialName("companyName") val companyName: String,
    @SerialName("position") val position: String,
    @SerialName("jobType") val jobType: JobType,
    @SerialName("employed") val employed: Boolean,
    @SerialName("startYm") val startYm: String,
    @SerialName("endYm") val endYm: String?,
)

@Serializable
data class LinkResponse(
    @SerialName("id") val id: Long,
    @SerialName("type") val type: LinkType,
    @SerialName("url") val url: String,
)

typealias CareerListResponseDto = List<CareerResponse>

@Serializable
data class LinkListResponseDto(
    @SerialName("links") val links: List<LinkResponse>,
)

fun CareerResponse.toEntity(): CareerItemEntity = CareerItemEntity(
    id = id, companyName = companyName, position = position,
    jobType = jobType, employed = employed, startYm = startYm, endYm = endYm
)

fun LinkResponse.toEntity(): LinkItemEntity = LinkItemEntity(
    id = id, type = type, url = url
)