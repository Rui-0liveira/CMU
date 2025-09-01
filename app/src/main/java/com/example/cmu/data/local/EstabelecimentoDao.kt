package com.example.cmu.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EstabelecimentoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirEstabelecimento(estabelecimento: EstabelecimentoEntity)

    @Query("SELECT * FROM estabelecimentos")
    suspend fun listarEstabelecimentos(): List<EstabelecimentoEntity>

    @Query("SELECT * FROM estabelecimentos WHERE id = :id LIMIT 1")
    suspend fun obterEstabelecimento(id: Int): EstabelecimentoEntity?
}
