package com.example.cmu.data.local

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PlacesScreen(viewModel: PlaceViewModel = viewModel()) {
    val places by viewModel.places.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { viewModel.loadPlaces("YOUR_API_KEY") }) {
            Text("Carregar pastelarias 🍩")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(places) { place ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(place.name ?: "Sem nome", style = MaterialTheme.typography.titleMedium)
                        Text(place.address ?: "Sem endereço", style = MaterialTheme.typography.bodyMedium)
                        Text("📍 ${place.lat}, ${place.lon}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
