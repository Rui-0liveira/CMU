package com.example.cmu.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cmu.data.local.DatabaseProvider
import com.example.cmu.data.local.PlaceEntity
import com.example.cmu.data.local.PlaceRepository
import com.example.cmu.data.local.PlaceViewModel
import com.example.cmu.data.viewmodel.PlaceViewModelFactory
import com.example.cmu.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceListScreen(
    navController: NavController
) {
    val context = LocalContext.current

    // Usando DatabaseProvider
    val dao = DatabaseProvider.getDatabase(context).placeDao()
    val repo = PlaceRepository(dao)
    val factory = PlaceViewModelFactory(repo)

    val viewModel: PlaceViewModel = viewModel(factory = factory)
    val places by viewModel.places.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Estabelecimentos") }) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(places) { place ->
                PlaceItem(place) {
                    navController.navigate(Screen.PlaceDetail.createRoute(place.id))
                }
            }
        }
    }
}



@Composable
fun PlaceItem(place: PlaceEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            place.name?.let { Text(text = it, style = MaterialTheme.typography.titleMedium) }
            place.address?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium) }
        }
    }
}
