package com.example.cmu.data.remote.models

data class PlacesResponse(
    val features: List<PlaceFeature>
)

data class PlaceFeature(
    val properties: PlaceProperties,
    val geometry: Geometry
)

data class PlaceProperties(
    val name: String?,
    val address_line1: String?,
    val address_line2: String?,
    val country: String?,
    val city: String?,
    val postcode: String?,
    val datasource: Datasource?
)

data class Geometry(
    val type: String,
    val coordinates: List<Double> // [lon, lat]
)

data class Datasource(
    val raw: Map<String, Any>?
)
