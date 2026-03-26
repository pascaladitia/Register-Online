package com.pascal.registeronline.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pascal.registeronline.data.local.dao.FavoritesDao
import com.pascal.registeronline.data.local.dao.ProfileDao
import com.pascal.registeronline.data.local.entity.FavoritesEntity
import com.pascal.registeronline.data.local.entity.ProfileEntity

@Database(
    entities = [
        ProfileEntity::class,
        FavoritesEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun favoritesDao(): FavoritesDao
}

