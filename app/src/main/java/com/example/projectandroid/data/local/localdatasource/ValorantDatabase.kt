package com.example.projectandroid.data.local.localdatasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projectandroid.data.local.dao.ValorantDao

@Database(entities = [AgentEntity::class], version = 1, exportSchema = false)
abstract class ValorantDatabase: RoomDatabase() {
    abstract val valorantDao: ValorantDao

    companion object{
        @Volatile
        private var INSTANCE: ValorantDatabase? =null

        fun getDatabase(context: Context): ValorantDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ValorantDatabase::class.java,
                    "valorant_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}