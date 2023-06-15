package com.example.mvvmquotesappkt

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun generate_numbers(){
        val random = (Math.random() * 6).toInt()
        assertTrue("The value of rollResult was not between 1 and 6", random in 1..6)

    }
}