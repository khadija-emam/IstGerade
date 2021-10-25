package com.istgerade.data.repository

import com.istgerade.data.entity.NumberResponse
import com.istgerade.data.entity.TranslatedTextResponse
import retrofit2.Response


interface Repository {
    suspend fun isEven(number: Long): NumberResponse?
    suspend fun translate(ad: String): TranslatedTextResponse


}