package com.example.mvvmquotesappkt.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmquotesappkt.api.QuoteService
import com.example.mvvmquotesappkt.db.QuoteDatabase
import com.example.mvvmquotesappkt.models.QuoteLists
import com.example.mvvmquotesappkt.utils.NetworkUtils

// it is used to manage the data this class is used to manage the data
class QuoteRepository(private val quoteService: QuoteService, private val quoteDatabase: QuoteDatabase,private val context: Context) {

    private val quoteLiveData = MutableLiveData<QuoteLists>()

    val liveData: LiveData<QuoteLists>
        get() = quoteLiveData

    suspend fun getQuotes(page: Int) {

        if (NetworkUtils.isNetworkAvailable(context)) {
            val result = quoteService.getQuotes(page)
            if (result?.body() != null) {
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                // pushing data into live data
                quoteLiveData.postValue(result.body())
            }
        } else {
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteLists(1, 1, 1, quotes, 1, 1)
            quoteLiveData.postValue(quoteList)

        }
    }
    // here we are storing ur data in database
    suspend fun getQuotesBackground(){
        val randomNumber = (Math.random() * 10).toInt()
        val result = quoteService.getQuotes(randomNumber)
        if(result?.body()!=null){
            quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
        }
    }
}