package com.ort.dogadoption.data.api

import com.ort.dogadoption.models.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class DogApiService {
     suspend fun getBreeds(): Response<ApiResponse> {
         return getRetrofit().create(DogApiInterface::class.java).getAllBreeds()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}