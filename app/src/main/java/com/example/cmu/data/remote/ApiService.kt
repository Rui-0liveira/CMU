package com.example.cmu.data.remote

import com.example.cmu.data.remote.models.PlacesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/places")
    suspend fun searchPlaces(
        @Query("categories") categories: String,
        @Query("filter") filter: String, // ex: "circle:-8.61,41.15,500"
        @Query("limit") limit: Int = 20,
        @Query("apiKey") apiKey: String
    ): PlacesResponse
}
