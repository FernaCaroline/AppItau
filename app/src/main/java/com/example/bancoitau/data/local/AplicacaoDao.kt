package com.example.bancoitau.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AplicacaoDao {

    @Query("SELECT * FROM aplicacoes ORDER BY dataMillis DESC")
    fun listar(): Flow<List<AplicacaoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(vararg itens: AplicacaoEntity): List<Long>

    @Update
    suspend fun atualizar(item: AplicacaoEntity)

    @Delete
    suspend fun deletar(item: AplicacaoEntity)

    @Query("DELETE FROM aplicacoes")
    suspend fun deletarTudo()

    @Query("SELECT COALESCE(SUM(valor), 0.0) FROM aplicacoes")
    fun saldoFlow(): Flow<Double>
}
