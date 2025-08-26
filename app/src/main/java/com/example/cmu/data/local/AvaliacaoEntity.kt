package com.example.cmu.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "avaliacoes")
data class AvaliacaoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val estabelecimentoId: Int,   // FK para Estabelecimento
    val utilizador: String,
    val estrelas: Int,
    val comentario: String,
    val fotoPath: String? = null,  // caminho local da imagem
    val timestamp: Long = System.currentTimeMillis()
)
