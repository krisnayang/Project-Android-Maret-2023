package com.example.projectandroid.data.local.localdatasource

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectandroid.data.local.model.Agent
import com.example.projectandroid.data.local.model.abilities

@Entity
data class AgentEntity (
    @PrimaryKey val uuid: String,
    @NonNull @ColumnInfo(name = "display_name") val displayName: String,
    @NonNull @ColumnInfo(name = "description") val description: String,
    @NonNull @ColumnInfo(name = "display_icon") val displayIcon: String,
    @NonNull @ColumnInfo(name = "ability_name") val abilityName: String,
    @ColumnInfo(name = "ability_icon") val abilityIcon: String,
    @NonNull @ColumnInfo(name = "ability_name2") val abilityName2: String,
    @ColumnInfo(name = "ability_icon2") val abilityIcon2: String,
    @NonNull @ColumnInfo(name = "ability_name3") val abilityName3: String,
    @ColumnInfo(name = "ability_icon3") val abilityIcon3: String,
    @NonNull @ColumnInfo(name = "ability_name4") val abilityName4: String,
    @ColumnInfo(name = "ability_icon4") val abilityIcon4: String,
        )

fun List<AgentEntity>.asDomainModel(): List<Agent> {
    return map {
        Agent(
            uuid = it.uuid,
            displayName = it.displayName,
            description = it.description,
            displayIcon = it.displayIcon,
            listOf(abilities(it.abilityName, it.abilityIcon)),
        )
    }
}