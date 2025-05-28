package com.ycosilvallana.onepiece.domain.model.base

import com.ycosilvallana.onepiece.domain.model.PagingMeta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BasePageResponse<T>(
    @SerialName("data") val data: T,
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
    @SerialName("http_status") val httpStatus: Int,
    @SerialName("meta") val meta: PagingMeta
)