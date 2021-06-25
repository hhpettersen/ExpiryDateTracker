package com.app.expirydatetracker.ui.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.expirydatetracker.models.ExpiryItem
import com.app.expirydatetracker.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun insertItem(item: ExpiryItem) {
        viewModelScope.launch {
            repository.insertItem(item)
        }
    }
}