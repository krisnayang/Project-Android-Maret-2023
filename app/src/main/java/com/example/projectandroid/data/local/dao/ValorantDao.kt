package com.example.projectandroid.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projectandroid.data.local.localdatasource.AgentEntity

@Dao
interface ValorantDao {
    //Belum Flow
    @Query("SELECT * FROM agententity")
    fun getAgents(): LiveData<List<AgentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( agents: List<AgentEntity>)

    @Query("SELECT * FROM agententity WHERE uuid = :id")
    fun getAgent(id: String): LiveData<AgentEntity>
}