package com.example.projectandroid.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.projectandroid.data.local.localdatasource.AgentEntity
import com.example.projectandroid.data.local.localdatasource.ValorantDatabase
import com.example.projectandroid.data.local.localdatasource.asDomainModel
import com.example.projectandroid.data.local.model.Agent
import com.example.projectandroid.data.repository.AgentRepository
import com.example.projectandroid.data.repository.AgentRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException

class AgentViewModel (
    application: Application
): AndroidViewModel(application){
    private val agentRepositoryImpl = AgentRepositoryImpl(ValorantDatabase.getDatabase(application))

    private val agents: LiveData<List<AgentEntity>> = agentRepositoryImpl.getAgents()
    fun getAgents(): LiveData<List<Agent>> = Transformations.map(agents){
        it.asDomainModel()
    }

    fun refreshAgent() = viewModelScope.launch {
        try {
            agentRepositoryImpl.refreshAgent()

        } catch (networkError: IOException) {

        }
    }
    init {
        refreshAgent()
    }

    class AgentViewModelFactory(
        val app: Application
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AgentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AgentViewModel(app) as T
            }

            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}
