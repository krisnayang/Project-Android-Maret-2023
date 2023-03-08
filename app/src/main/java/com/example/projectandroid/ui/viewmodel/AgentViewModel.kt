package com.example.projectandroid.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.projectandroid.data.local.dao.ValorantDao
import com.example.projectandroid.data.local.localdatasource.DatabaseAgent
import com.example.projectandroid.data.local.localdatasource.ValorantDatabase
import com.example.projectandroid.data.repository.AgentRepository
import com.example.projectandroid.data.repository.AgentRepositoryImpl
import kotlinx.coroutines.launch
import java.io.IOException

class AgentViewModel (
    application: Application
): AndroidViewModel(application){
    private val agentRepository = AgentRepositoryImpl(ValorantDatabase.getDatabase(application))
    init {
        viewModelScope.launch{
            try {
                agentRepository.refreshAgent()

            } catch (networkError: IOException) {

            }
        }
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
