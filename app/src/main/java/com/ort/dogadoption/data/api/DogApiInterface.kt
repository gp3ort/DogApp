package com.ort.dogadoption.data.api

import com.ort.dogadoption.models.ApiResponse
import com.ort.dogadoption.models.ImageResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface DogApiInterface {
    @GET("breeds/list/all")
    suspend fun getAllBreeds(): Response<ApiResponse>

    @GET("breed/{breed}/images/random")
    fun getPictures(@Path("breed") breed:String): Call<ImageResponse>
}