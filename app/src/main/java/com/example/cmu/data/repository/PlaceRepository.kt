package com.example.cmu.data.local


import com.example.cmu.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class PlaceRepository(private val dao: PlaceDao) {

    fun getPlaces(): Flow<List<PlaceEntity>> = dao.getAllPlaces()

    suspend fun fetchAndSavePlaces(apiKey: String) {
        val response = RetrofitInstance.api.searchPlaces(
            categories = "catering.cafe",
            filter = "circle:-8.61,41.15,1000", // Porto, raio 1km
            apiKey = apiKey
        )
        val entities = response.features.map {
            PlaceEntity(
                name = it.properties.name,
                address = it.properties.address_line1,
                lat = it.geometry.coordinates[1],
                lon = it.geometry.coordinates[0]
            )
        }
        dao.insertPlaces(entities)
    }
}
