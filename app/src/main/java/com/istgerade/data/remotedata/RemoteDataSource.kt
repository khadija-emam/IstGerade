package com.istgerade.data.remotedata

import com.istgerade.data.entity.NumberResponse
import com.istgerade.data.entity.TranslatedTextResponse
import retrofit2.Response


interface RemoteDataSource {
    suspend fun isEven(number: Long): NumberResponse?
    suspend fun translate(ad: String): TranslatedTextResponse
}