package com.example.kilohealth.localconfig

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kilohealth.feature.feature_home.data.local.dao.BlogListDao
import com.example.kilohealth.feature.feature_home.data.local.entity.BlogListEntity

@Database(
    entities = [BlogListEntity::class,],
    version = 1
)

abstract class LocalDatabase : RoomDatabase() {
    abstract val dao : BlogListDao
}

fun provideLocalDb(app: Context): LocalDatabase {
    return Room.databaseBuilder(
        context = app,
        LocalDatabase::class.java,
        "HealthDB"
    ).build()
}