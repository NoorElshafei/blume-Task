package com.example.blumetask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.blumetask.model.CartRoomModel

@Database(entities = [CartRoomModel::class], version = 1, exportSchema = false)
abstract class CartRoomDataBase : RoomDatabase() {
    abstract fun userDao(): RoomDao
}