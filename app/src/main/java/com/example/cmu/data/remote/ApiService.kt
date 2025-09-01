package com.example.cmu.data.remote

import com.example.cmu.data.remote.models.PlacesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("maps/api/place/nearbysearch/json")
    suspend fun searchNearby(
        @Query("location") location: String,   // "41.1579,-8.6291"
        @Query("radius") radius: Int,         // em metros
        @Query("type") type: String,          // "restaurant", "cafe", etc.
        @Query("key") key: String
    ): PlacesResponse
}
