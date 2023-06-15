package com.example.mvvmquotesappkt

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmquotesappkt.api.QuoteService
import com.example.mvvmquotesappkt.api.RetrofitHelper
import com.example.mvvmquotesappkt.repository.QuoteRepository
import com.example.mvvmquotesappkt.viewModels.MainViewModel
import com.example.mvvmquotesappkt.viewModels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // here we are doing type casting
        val repository = (application as QuoteApplication).quoteRepository
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)
        mainViewModel.quotes.observe(this,Observer{
            Toast.makeText(this@MainActivity,it.results.size.toString(),Toast.LENGTH_SHORT).show()
        })
    }
}