package com.hsLink.hslink.data.dto.request.onboarding

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnboardingRequest (
    @SerialName("name")
    val name: String,
    @SerialName("major")
    val major: String,
    @SerialName("studentNumber")
    val studentNumber: String,
    @SerialName("jobSeeking")
    val jobSeeking : Boolean,
    @SerialName("mentor")
    val mentor : Boolean,
    @SerialName("email")
    val email : String
    )