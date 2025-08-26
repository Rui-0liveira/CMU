package com.example.cmu.data.local

import android.content.Context
import androidx.room.Room

object DatabaseModule {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "cmu_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
