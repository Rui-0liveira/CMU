package com.example.cmu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmu.data.local.AvaliacaoEntity
import com.example.cmu.viewmodel.AvaliacaoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(
    placeId: Int,
    viewModel: AvaliacaoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var avaliacoes by remember { mutableStateOf<List<AvaliacaoEntity>>(emptyList()) }

    // Carregar últimas 10 avaliações
    LaunchedEffect(placeId) {
        viewModel.listarUltimas(placeId) { avaliacoes = it }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Detalhes") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Text("Estabelecimento ID: $placeId", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Últimas Avaliações", style = MaterialTheme.typography.titleMedium)

            LazyColumn {
                items(avaliacoes) { avaliacao ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("⭐ ${avaliacao.estrelas} - ${avaliacao.utilizador}")
                            Text(avaliacao.comentario)
                        }
                    }
                }
            }
        }
    }
}
