package com.example.mvvmquotesappkt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmquotesappkt.models.Result

@Database(entities = [Result::class], version = 2)
abstract class QuoteDatabase : RoomDatabase(){

    abstract fun quoteDao():QuoteEntity
    companion object {
        // whenever any value assign to the instance variable so that every thread get to know about the updated value that's why we use volatile here
        @Volatile
        // this will hold the database
        private var INSTANCE: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                // there will be possibility that this will create multiple instance so we put here locking to prevent from making multiple instance
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            QuoteDatabase::class.java,
                            "quoteDB"
                        )
                            .build()
                }
            }

            return INSTANCE!!
        }
    }
}