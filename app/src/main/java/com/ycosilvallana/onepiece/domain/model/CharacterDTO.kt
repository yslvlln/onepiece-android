package com.ycosilvallana.onepiece.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDTO(
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>
) {
    companion object {
        fun CharacterDTO.toEntity(): CharacterEntity = CharacterEntity(
            id, name, image, about, rating, power, month, day, family, abilities, natureTypes
        )
    }
}