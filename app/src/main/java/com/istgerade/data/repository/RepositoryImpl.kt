package com.istgerade.data.repository

import com.istgerade.data.entity.NumberResponse
import com.istgerade.data.entity.TranslatedTextResponse
import com.istgerade.data.remotedata.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource):
    Repository {
    override suspend fun isEven(number: Long): NumberResponse? {
        return remoteDataSource.isEven(number)
    }

    override suspend fun translate(ad: String): TranslatedTextResponse {
        return remoteDataSource.translate(ad)
    }

}