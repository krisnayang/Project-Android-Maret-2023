package com.example.projectandroid.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projectandroid.data.local.localdatasource.DatabaseAgent

@Dao
interface ValorantDao {
    //Belum Flow
    @Query("SELECT * FROM databaseagent")
    fun getAgents(): LiveData<List<DatabaseAgent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( agents: List<DatabaseAgent>)
}