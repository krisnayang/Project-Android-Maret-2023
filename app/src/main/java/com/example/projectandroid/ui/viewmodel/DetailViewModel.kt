package com.example.projectandroid.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.projectandroid.data.local.localdatasource.AgentEntity
import com.example.projectandroid.data.local.localdatasource.ValorantDatabase
import com.example.projectandroid.data.repository.AgentRepositoryImpl

class DetailViewModel (
    application: Application
): AndroidViewModel(application){
    private val agentRepositoryImpl = AgentRepositoryImpl(ValorantDatabase.getDatabase(application))

    fun retrieveAgent(id: String): LiveData<AgentEntity> = agentRepositoryImpl.getAgent(id).asLiveData()

    class Factory(
        val app: Application
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailViewModel(app) as T
            }

            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}