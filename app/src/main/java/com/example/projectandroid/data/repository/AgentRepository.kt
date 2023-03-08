package com.example.projectandroid.data.repository


interface AgentRepository{
    suspend fun refreshAgent()
}