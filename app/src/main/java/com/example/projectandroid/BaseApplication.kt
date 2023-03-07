package com.example.projectandroid

import android.app.Application
import com.example.projectandroid.data.local.localdatasource.ValorantDatabase

class BaseApplication : Application() {
    val database: ValorantDatabase by lazy { ValorantDatabase.getDatabase(this) }
}