package com.example.projectandroid.data.remote.remotedatasource

import com.example.projectandroid.data.local.localdatasource.AgentEntity
import com.example.projectandroid.data.remote.model.Agent
import com.example.projectandroid.data.remote.model.abilities
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
    val displayIcon: String,
    val abilities: List<abilities>
)

fun CharactersResponse.asDomainModel(): List<Agent> {
    return data.map {
        Agent(
            uuid = it.uuid,
            displayName = it.displayName,
            displayIcon = it.displayIcon,
            description = it.description,
            abilities = it.abilities
        )
    }
}

fun CharactersResponse.asDatabaseModel(): List<AgentEntity> {
    return data.map {
        AgentEntity(
            uuid = it.uuid,
            displayName = it.displayName,
            description = it.description,
            displayIcon = it.displayIcon,
            abilityName = it.abilities[0].displayName,
            abilityIcon = it.abilities[0].displayIcon!!,
            abilityName2 = it.abilities[1].displayName,
            abilityIcon2 = it.abilities[1].displayIcon!!,
            abilityName3 = it.abilities[2].displayName,
            abilityIcon3 = it.abilities[2].displayIcon!!,
            abilityName4 = it.abilities[3].displayName,
            abilityIcon4 = it.abilities[3].displayIcon!!,
        )
    }
}

