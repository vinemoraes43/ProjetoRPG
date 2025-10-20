package com.example.rpgfront.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rpgfront.data.local.dao.PersonagemDao
import com.example.rpgfront.data.local.entity.PersonagemEntity

@Database(entities = [PersonagemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personagemDao(): PersonagemDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "rpg_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}