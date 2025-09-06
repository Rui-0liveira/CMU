package com.example.cmu.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estabelecimentos")
data class EstabelecimentoEntity(
    @PrimaryKey val placeId: String, // id vindo da API (ou gerado)
    val nome: String,
    val endereco: String?,
    val telefone: String?,
    val lat: Double,
    val lng: Double,
    val mediaPontuacao: Double = 0.0,
    val totalAvaliacoes: Int = 0
)
