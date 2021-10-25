package com.istgerade.data.remotedata

import android.util.Log
import com.google.gson.Gson
import com.istgerade.data.entity.NumberResponse
import com.istgerade.data.entity.TranslatedTextResponse
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val retrofitService: RetrofitService) :
    RemoteDataSource {
    override suspend fun isEven(number: Long): NumberResponse? {
       val result=retrofitService.isEven(number = number)
        if (result.isSuccessful){
            return result.body()
        }else{
            val gson = Gson()
            val eventResponse =
                gson.fromJson(result.errorBody()!!.string(), NumberResponse::class.java)
            throw Exception(eventResponse.error)
        }


    }

    override suspend fun translate(ad: String): TranslatedTextResponse {
        return retrofitService.translate(ad = ad)
    }

}