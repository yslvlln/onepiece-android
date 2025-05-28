package com.ycosilvallana.onepiece.domain.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseErrorResponse<T>(
    @SerialName("meta") val meta: T? = null,
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
    @SerialName("error_code") val errorCode: String,
    @SerialName("http_status") val httpStatus: Int,
)