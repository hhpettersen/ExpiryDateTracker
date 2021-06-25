package com.app.expirydatetracker.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.expirydatetracker.helpers.Resource
import com.app.expirydatetracker.models.ExpiryItem
import com.app.expirydatetracker.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val readAllData: LiveData<List<ExpiryItem>> = repository.readAllData

    fun deleteItem(item: ExpiryItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}