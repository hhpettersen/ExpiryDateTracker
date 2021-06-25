package com.app.expirydatetracker.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.expirydatetracker.helpers.DateHelper
import com.app.expirydatetracker.helpers.Recycler
import com.app.expirydatetracker.ui.additem.ItemCategories
import java.util.*

@Entity(tableName = "expiring_items")
data class ExpiryItem(
    val name: String = "",
    val category: ItemCategories = ItemCategories.UNDEFINED,
    val dateAdded: Long = 0,
    val dateExpiring: Long = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) : Recycler.RenderModel {
    val dateAddedFormatted: String
        get() {
            return DateHelper.formatDateToString(dateAdded)
        }
    val dateExpiringFormatted: String
        get() {
            return DateHelper.formatDateToString(dateExpiring)
        }
}