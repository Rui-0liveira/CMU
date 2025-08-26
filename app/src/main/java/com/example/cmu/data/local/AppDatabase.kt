package com.example.cmu.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EstabelecimentoEntity::class, AvaliacaoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun estabelecimentoDao(): EstabelecimentoDao
    abstract fun avaliacaoDao(): AvaliacaoDao
}
