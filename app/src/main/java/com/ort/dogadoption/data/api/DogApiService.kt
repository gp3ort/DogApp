package com.ort.dogadoption.data.api

import com.ort.dogadoption.models.ApiResponse
import com.ort.dogadoption.models.ImageResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class DogApiService {
     suspend fun getBreeds(): Response<ApiResponse> {
         return getRetrofit().create(DogApiInterface::class.java).getAllBreeds()
    }

    fun getPicture(breed: String): Call<ImageResponse> {
        return getRetrofit().create(DogApiInterface::class.java).getPictures(breed)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}