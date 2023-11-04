package com.ort.dogadoption.data.api

import com.ort.dogadoption.models.ApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface DogApiInterface {
    @GET("list/all")
    suspend fun getAllBreeds(): Response<ApiResponse>

}