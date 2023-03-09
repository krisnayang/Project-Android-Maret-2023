package com.example.projectandroid.data.repository

import androidx.lifecycle.asLiveData
import com.example.projectandroid.data.local.localdatasource.ValorantDatabase
import com.example.projectandroid.data.remote.api.Api
import com.example.projectandroid.data.remote.remotedatasource.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AgentRepositoryImpl(private val database: ValorantDatabase):AgentRepository {
    override suspend fun refreshAgent(){
        withContext(Dispatchers.IO){
            val agent = Api.retrofitService.getAgents()
            database.valorantDao.insertAll(agent.asDatabaseModel())
        }
    }

    fun getAgents() = database.valorantDao.getAgents().asLiveData()
    fun getAgent(id: String) = database.valorantDao.getAgent(id)
}