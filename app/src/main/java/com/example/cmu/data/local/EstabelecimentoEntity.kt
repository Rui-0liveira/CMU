package com.example.cmu.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estabelecimentos")
data class EstabelecimentoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val latitude: Double,
    val longitude: Double,
    val telefone: String? = null,
    val pontuacaoMedia: Double = 0.0
)
