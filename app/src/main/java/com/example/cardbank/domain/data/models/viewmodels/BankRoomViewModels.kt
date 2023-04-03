package com.example.cardbank.domain.data.models.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardbank.domain.data.models.base.Bank
import com.example.cardbank.data.local.room.RepositoryRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BankRoomViewModels(private val repository: RepositoryRoom) : ViewModel() {

    private val _repos = MutableLiveData<List<Bank>>()
    val repos: LiveData<List<Bank>> = _repos

    fun onShowListRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            var result = repository.getAllHistory()
            withContext(Dispatchers.Main) {
                _repos.postValue(result)
            }
        }
    }
}