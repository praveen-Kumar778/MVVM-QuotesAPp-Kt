package com.example.mvvmquotesappkt.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mvvmquotesappkt.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// this we do background work automatically and notify the user if a new quote will be added
class QuoteWorker(private val context: Context, params : WorkerParameters) : Worker(context,params) {
    override fun doWork(): Result {
        Log.d("TAG","Worker Called")
        val repository = (context as QuoteApplication).quoteRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuotesBackground()
        }
        return Result.success()
    }
}