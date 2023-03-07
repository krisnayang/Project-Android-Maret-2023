package com.example.projectandroid.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.projectandroid.data.local.localdatasource.DatabaseAgent
import com.example.projectandroid.data.local.localdatasource.ValorantDatabase
import com.example.projectandroid.data.local.localdatasource.asDomainModel
import com.example.projectandroid.data.remote.api.Api
import com.example.projectandroid.data.remote.api.CharacterResponse
import com.example.projectandroid.data.remote.remotedatasource.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AgentRepository(private val database: ValorantDatabase) {
    suspend fun refreshAgent(){
        withContext(Dispatchers.IO){
            val agent = Api.retrofitService.getAgents()
            database.valorantDao.insertAll(agent.asDatabaseModel())
        }
    }

    val agents: LiveData<List<CharacterResponse>> = Transformations.map(database.valorantDao.getAgents()){
        it.asDomainModel()
    }
}