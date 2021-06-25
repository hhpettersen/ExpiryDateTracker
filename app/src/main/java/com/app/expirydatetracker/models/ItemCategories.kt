package com.app.expirydatetracker.models

import com.app.expirydatetracker.R

enum class ItemCategories(
    val category: String,
    val icon: Int
) {
    UNDEFINED("Undefined", R.drawable.ic_expired),
    FOOD("Food", R.drawable.ic_baseline_set_meal_24),
    GIFT_CARD("Gift card", R.drawable.ic_baseline_card_giftcard_24),
    RETURNS("Returns", R.drawable.ic_baseline_assignment_return_24)
}