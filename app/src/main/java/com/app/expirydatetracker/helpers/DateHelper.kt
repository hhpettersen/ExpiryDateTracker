package com.app.expirydatetracker.helpers

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateHelper {
    val today: Long = System.currentTimeMillis()

    private val norwegianLocale: Locale
        get() {
            return Locale("no", "NO")
        }

    const val oneDayUnix = 86400000

    fun rangeToDaysBetween(from: Long?, to: Long?): Long? {
        return from?.let { to?.minus(it)?.let { TimeUnit.MILLISECONDS.toDays(it) + 1 } }
    }

//    fun isDateInFuture(visit: VisitRich): Boolean {
//        val today = Calendar.getInstance().timeInMillis
//        return if (visit.singleDate != null) {
//            visit.singleDate < today
//        } else {
//            visit.fromDate!! < today && visit.toDate!! < today
//        }
//    }

    fun today() {

    }

    fun formatToDateString(date: Long): String {
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
        return formatter.format(date)
    }

    fun formatDateToString(date: Long?) : String {
        val formatter = SimpleDateFormat("dd.MM.yy", Locale.ENGLISH)
        return formatter.format(date)
    }
}