package com.example.mvvmquotesappkt

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mvvmquotesappkt.api.QuoteService
import com.example.mvvmquotesappkt.api.RetrofitHelper
import com.example.mvvmquotesappkt.db.QuoteDatabase
import com.example.mvvmquotesappkt.repository.QuoteRepository
import com.example.mvvmquotesappkt.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication: Application() {

    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }

    private fun setUpWorker() {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java,30,TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueue(workRequest)

    }

    private fun initialize() {
        val service = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(service,database,applicationContext)
    }
}