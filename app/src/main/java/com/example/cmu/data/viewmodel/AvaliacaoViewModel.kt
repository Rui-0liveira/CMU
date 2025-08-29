package com.example.cmu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmu.data.local.AvaliacaoEntity
import com.example.cmu.data.repository.AvaliacaoRepository
import kotlinx.coroutines.launch

class AvaliacaoViewModel(private val repository: AvaliacaoRepository) : ViewModel() {

    fun adicionarAvaliacao(avaliacao: AvaliacaoEntity) {
        viewModelScope.launch {
            repository.adicionarAvaliacao(avaliacao)
        }
    }

    fun syncFromFirebase() {
        repository.syncFromFirebase()
    }

    fun syncPending() {
        viewModelScope.launch {
            repository.syncPending()
        }
    }

    fun listarUltimas(estabelecimentoId: Int, callback: (List<AvaliacaoEntity>) -> Unit) {
        viewModelScope.launch {
            val result = repository.listarUltimas(estabelecimentoId)
            callback(result)
        }
    }

    fun listarHistorico(callback: (List<AvaliacaoEntity>) -> Unit) {
        viewModelScope.launch {
            val result = repository.listarHistorico()
            callback(result)
        }
    }
}
