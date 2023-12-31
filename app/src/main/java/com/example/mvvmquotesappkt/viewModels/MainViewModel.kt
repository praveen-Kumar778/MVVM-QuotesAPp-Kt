package com.example.mvvmquotesappkt.viewModels

import androidx.lifecycle.LiveData
import com.example.mvvmquotesappkt.repository.QuoteRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmquotesappkt.models.QuoteLists
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getQuotes(1)
        }
    }

    val quotes : LiveData<QuoteLists>
    get() = repository.liveData
}