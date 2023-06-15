package com.example.mvvmquotesappkt.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmquotesappkt.models.Result
@Dao
interface QuoteEntity{

@Insert
suspend fun addQuotes(quotes:List<Result>)

@Query("SELECT * FROM quote")
suspend fun getQuotes() : List<Result>
}