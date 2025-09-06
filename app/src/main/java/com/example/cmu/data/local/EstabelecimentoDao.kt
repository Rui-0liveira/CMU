package com.example.cmu.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EstabelecimentoDao {

    @Query("SELECT * FROM estabelecimentos")
    fun getAllEstabelecimentos(): Flow<List<EstabelecimentoEntity>>

    @Query("SELECT * FROM estabelecimentos WHERE placeId = :id LIMIT 1")
    suspend fun getById(id: String): EstabelecimentoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(est: EstabelecimentoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<EstabelecimentoEntity>)

    @Query("DELETE FROM estabelecimentos")
    suspend fun clearAll()
}
