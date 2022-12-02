package com.scientifichamster.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scientifichamster.local.model.PostLocalModel

@Database(entities = [PostLocalModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun postDao(): PostDAO
}