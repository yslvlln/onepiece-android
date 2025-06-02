package com.ycosilvallana.onepiece.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterPageResponse(
    @SerialName("data") val data: List<CharacterDTO>,
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
    @SerialName("http_status") val httpStatus: Int,
    @SerialName("meta") val meta: PagingMeta
)
