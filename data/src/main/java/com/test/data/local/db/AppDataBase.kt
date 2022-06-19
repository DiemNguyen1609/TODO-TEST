package com.test.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.data.entities.SellEntity
import com.test.data.local.db.dao.TodoDao


fun createSellDao(context: Context) = createAppDao(context).sellDao()

fun createAppDao(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, "app_db").allowMainThreadQueries().build()

@Database(entities = [SellEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sellDao(): TodoDao
}