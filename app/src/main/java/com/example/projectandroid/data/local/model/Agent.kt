package com.example.projectandroid.data.local.model

data class Agent (
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val abilities: List<abilities>
)

data class abilities(
    val displayName: String,
    val displayIcon: String?,
)