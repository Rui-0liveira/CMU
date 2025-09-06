package com.example.cmu.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.cmu.data.local.EstabelecimentoEntity
import com.example.cmu.data.viewmodel.MapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapScreen(
    hasLocationPermission: Boolean,
    viewModel: MapViewModel? = null, // pode vir injetado mais tarde
    onMarkerClick: (String) -> Unit = {}
) {
    val estabelecimentos by (viewModel?.estabelecimentos ?: kotlinx.coroutines.flow.flowOf(emptyList()))
        .collectAsState(initial = emptyList())

    val defaultLocation = LatLng(41.1579, -8.6291) // Porto
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 13f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
            uiSettings = MapUiSettings(myLocationButtonEnabled = hasLocationPermission)
        ) {
            estabelecimentos.forEach { est: EstabelecimentoEntity ->
                Marker(
                    state = MarkerState(position = LatLng(est.lat, est.lng)),
                    title = est.nome,
                    snippet = "⭐ ${"%.1f".format(est.mediaPontuacao)} — ${est.totalAvaliacoes} reviews",
                    onClick = {
                        onMarkerClick(est.placeId)
                        false
                    }
                )
            }
        }
    }
}
