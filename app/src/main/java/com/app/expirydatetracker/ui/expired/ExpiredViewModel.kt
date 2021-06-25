package com.app.expirydatetracker.ui.expired

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
class ExpiredViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val readAllDataExpired: LiveData<List<ExpiryItem>> = repository.readAllDataExpired

}