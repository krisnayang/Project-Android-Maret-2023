package com.example.projectandroid.data.local.localdatasource

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectandroid.data.remote.api.CharacterResponse
import com.example.projectandroid.data.remote.api.abilities

@Entity
data class DatabaseAgent (
    @PrimaryKey val uuid: String,
    @NonNull @ColumnInfo(name = "display_name") val displayName: String,
    @NonNull @ColumnInfo(name = "description") val description: String,
    @NonNull @ColumnInfo(name = "ability_name") val abilityName: String,
    @ColumnInfo(name = "ability_icon") val abilityIcon: String,
        )

fun List<DatabaseAgent>.asDomainModel(): List<CharacterResponse> {
    return map {
        CharacterResponse(
            uuid = it.uuid,
            displayName = it.displayName,
            description = it.description,
            listOf(abilities(it.abilityName, it.abilityIcon)),
        )
    }
}