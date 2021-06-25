package com.app.expirydatetracker.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.expirydatetracker.models.ExpiryItem

@Database(
    entities = [ExpiryItem::class], // Tell the database the entries will hold data of this type
    version = 5
)

abstract class Database : RoomDatabase() {
    abstract fun getDao(): DAO
}