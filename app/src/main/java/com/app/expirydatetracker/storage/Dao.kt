package com.app.expirydatetracker.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.expirydatetracker.models.ExpiryItem

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ExpiryItem?)

    @Query("SELECT * FROM expiring_items WHERE :date <= dateExpiring ORDER BY dateExpiring ASC")
    fun readActiveItems(date: Long): LiveData<List<ExpiryItem>>

    @Query("SELECT * FROM expiring_items WHERE :date > dateExpiring ORDER BY dateExpiring DESC")
    fun readExpiredItems(date: Long): LiveData<List<ExpiryItem>>

    @Delete
    suspend fun deleteItem(item: ExpiryItem)

    @Update
    suspend fun updateItem(item: ExpiryItem)
}