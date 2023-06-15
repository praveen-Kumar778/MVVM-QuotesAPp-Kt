package com.example.mvvmquotesappkt.api

import com.example.mvvmquotesappkt.models.QuoteLists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): Response<QuoteLists>

    // BASEURL + /quotes?page=1
}