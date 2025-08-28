package com.example.cmu.data.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlaceViewModel(private val repository: PlaceRepository) : ViewModel() {

    val places = repository.getPlaces()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun loadPlaces(apiKey: String) {
        viewModelScope.launch {
            repository.fetchAndSavePlaces(apiKey)
        }
    }
}
