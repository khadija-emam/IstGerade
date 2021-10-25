package com.istgerade.data.remotedata


import com.istgerade.data.entity.NumberResponse
import com.istgerade.data.entity.TranslatedTextResponse
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {
    @GET("api/iseven/{number}")
    suspend fun isEven(
        @Path("number") number: Long
    ): Response<NumberResponse>

    @POST
    suspend fun translate(
        @Url url: String = "https://libretranslate.de/translate",
        @Query("source") source: String = "en",
        @Query("target") target: String = "de",
        @Query("q") ad: String,
    ): TranslatedTextResponse
}