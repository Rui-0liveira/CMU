package com.example.cmu.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmu.data.repository.EstabelecimentoRepository
import com.example.cmu.data.local.EstabelecimentoEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MapViewModel(
    private val repo: EstabelecimentoRepository
) : ViewModel() {

    // Expor lista como StateFlow para Compose
    val estabelecimentos: StateFlow<List<EstabelecimentoEntity>> =
        repo.getAll()
            .map { list -> list.sortedByDescending { it.mediaPontuacao } } // ordena por pontuação
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Exemplo: atualizar com dados da API (chamada ao repo)
    fun updateFromApi(apiData: List<EstabelecimentoEntity>) {
        viewModelScope.launch {
            repo.refreshFromApi(apiData)
        }
    }
}
