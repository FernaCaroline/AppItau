package com.example.bancoitau.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TransacaoDao {

    @Query("SELECT * FROM transacoes ORDER BY dataMillis DESC")
    fun listar(): Flow<List<TransacaoEntity>>   // <- NÃO é suspend

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(vararg itens: TransacaoEntity): List<Long>

    @Update
    suspend fun atualizar(item: TransacaoEntity)

    @Delete
    suspend fun deletar(item: TransacaoEntity)

    @Query("DELETE FROM transacoes")
    suspend fun deletarTudo()
}
