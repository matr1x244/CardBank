package com.example.cardbank.domain.data.models.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardbank.domain.data.models.RepositoryBank
import com.example.cardbank.domain.data.models.base.Bank
import com.example.cardbank.domain.data.models.base.BankBIN
import kotlinx.coroutines.*

class MainViewModels(private val repository: RepositoryBank) : ViewModel() {

    private val _card = MutableLiveData<BankBIN>()
    val card: LiveData<BankBIN> = _card

    private val _history = MutableLiveData<Bank>()
    val history: LiveData<Bank> = _history

    fun onShowCard(textNumber : String) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.v("@@@", "No success $throwable")
        }
        var newStart: Job? = null
        newStart?.cancel()
        newStart = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = repository.observerBin(textNumber)
            withContext(Dispatchers.Main) {
                _card.postValue(result)
            }
        }
    }

    fun onSaveBank(bank: Bank) {
        viewModelScope.launch(Dispatchers.IO) {
            val bank = repository.saveBin(bank)
            withContext(Dispatchers.Main) {
                _history.value
            }
        }
    }

}
