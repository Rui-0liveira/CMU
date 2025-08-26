package com.example.cmu.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AvaliacaoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirAvaliacao(avaliacao: AvaliacaoEntity)

    @Query("SELECT * FROM avaliacoes WHERE estabelecimentoId = :estabelecimentoId ORDER BY timestamp DESC LIMIT 10")
    suspend fun listarUltimasAvaliacoes(estabelecimentoId: Int): List<AvaliacaoEntity>

    @Query("SELECT * FROM avaliacoes ORDER BY timestamp DESC")
    suspend fun listarHistorico(): List<AvaliacaoEntity>
}
