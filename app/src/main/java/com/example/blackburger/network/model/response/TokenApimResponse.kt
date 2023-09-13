package com.example.blackburger.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenApimResponse(
    @SerialName("access_token")
    val mAccessToken: String? = null,
    @SerialName("token_type")
    val tokenType: String? = null,
    @SerialName("scope")
    val scope: String? = null,
    @SerialName("expires_in")
    val mExpiresIn: Long = 0,
)
