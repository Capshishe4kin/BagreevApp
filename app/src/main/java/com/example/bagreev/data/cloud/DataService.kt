package com.example.bagreev.data.cloud

import com.example.bagreev.data.model.DataResult
import retrofit2.Response
import retrofit2.http.GET

interface DataService {
    @GET("gifs/trending?api_key=wDTtPVCQcYrcewS82oRjkdtbKlQU7SUW")
    suspend fun getGifs(): Response<DataResult>
}