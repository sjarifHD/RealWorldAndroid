package com.sjarifhd.realworldandroid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class RealWorldDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: RealWorldDatabase? = null

        fun getDatabase(context: Context): RealWorldDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RealWorldDatabase::class.java,
                    "realWorldDb"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}