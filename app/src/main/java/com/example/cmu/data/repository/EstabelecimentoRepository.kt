package com.example.cmu.data.repository

import com.example.cmu.data.local.EstabelecimentoDao
import com.example.cmu.data.local.EstabelecimentoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class EstabelecimentoRepository(
    private val dao: EstabelecimentoDao
) {
    fun getAll(): Flow<List<EstabelecimentoEntity>> = dao.getAllEstabelecimentos()

    suspend fun refreshFromApi(apiList: List<EstabelecimentoEntity>) {
        dao.insertAll(apiList)
    }

    suspend fun insert(est: EstabelecimentoEntity) {
        dao.insert(est)
    }

    suspend fun getById(id: String) = dao.getById(id)
}
