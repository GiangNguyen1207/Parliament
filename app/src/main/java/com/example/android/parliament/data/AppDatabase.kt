package com.example.android.parliament.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ParliamentMember::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (db != null) return db as AppDatabase

            synchronized(this) {
                val appDb = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app-database"
                )
                    .build()

                db = appDb
                return appDb
            }
        }
    }
}