package com.example.projectandroid.data.remote.api

data class CharacterResponse (
    val uuid: String,
    val displayName: String,
    val description: String,
    val abilities: List<abilities>
)

data class abilities(
    val displayName: String,
    val displayIcon: String?,
)

//data class Valorant(
//    val displayName: String = "",
//    val description: String = "",
//    val fullPotrait: String = "",
//)

//data class RoleResponse(
//    val displayName: String = "",
//    val displayIcon: String = "",
//)