package com.example.projectandroid.data.remote.remotedatasource

import com.example.projectandroid.data.local.localdatasource.DatabaseAgent
import com.example.projectandroid.data.remote.api.CharacterResponse
import com.example.projectandroid.data.remote.api.abilities
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CharactersResponse(
//    @SerializedName("data")
    val data: List<NetworkAgent>
)

@JsonClass(generateAdapter = true)
data class NetworkAgent (
    val uuid: String,
    val displayName: String,
    val description: String,
    val abilities: List<abilities>
)

fun CharactersResponse.asDomainModel(): List<CharacterResponse> {
    return data.map {
        CharacterResponse(
            uuid = it.uuid,
            displayName = it.displayName,
            description = it.description,
            abilities = it.abilities
        )
    }
}

/**
 * Convert Network results to database objects
 */
fun CharactersResponse.asDatabaseModel(): List<DatabaseAgent> {
    return data.map {
        DatabaseAgent(
            uuid = it.uuid,
            displayName = it.displayName,
            description = it.description,
            abilityName = it.abilities.get(3).displayName,
            abilityIcon = it.abilities.get(3).displayIcon!!,
        )
    }
}

