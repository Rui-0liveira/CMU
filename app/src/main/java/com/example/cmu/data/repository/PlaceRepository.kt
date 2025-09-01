package com.example.cmu.data.local

import com.example.cmu.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class PlaceRepository(private val dao: PlaceDao) {

    fun getPlaces(): Flow<List<PlaceEntity>> = dao.getAllPlaces()

    suspend fun fetchAndSavePlaces(apiKey: String) {
        val response = RetrofitInstance.api.searchNearby(
            location = "41.1579,-8.6291", // Porto
            radius = 1000,
            type = "restaurant",
            key = apiKey
        )

        if (response.status == "OK") {
            val entities = response.results.map {
                PlaceEntity(
                    name = it.name,
                    address = it.vicinity,
                    lat = it.geometry.location.lat,
                    lon = it.geometry.location.lng
                )
            }
            dao.insertPlaces(entities)
        }
    }
}
