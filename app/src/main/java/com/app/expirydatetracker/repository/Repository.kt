package com.app.expirydatetracker.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.app.expirydatetracker.helpers.DateHelper
import com.app.expirydatetracker.storage.DAO
import com.app.expirydatetracker.models.ExpiryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val DAO: DAO
) {

    val readAllData: LiveData<List<ExpiryItem>> = DAO.readActiveItems(DateHelper.today)

    val readAllDataExpired: LiveData<List<ExpiryItem>> = DAO.readExpiredItems(DateHelper.today)

    @WorkerThread
    suspend fun insertItem(highScores: ExpiryItem?) = withContext(Dispatchers.IO) {
        DAO.insertItem(highScores)
    }

    @WorkerThread
    suspend fun deleteItem(item: ExpiryItem) = withContext(Dispatchers.IO) {
        DAO.deleteItem(item)
    }

    @WorkerThread
    suspend fun updateItem(item: ExpiryItem) = withContext(Dispatchers.IO) {
        DAO.updateItem(item)
    }
}