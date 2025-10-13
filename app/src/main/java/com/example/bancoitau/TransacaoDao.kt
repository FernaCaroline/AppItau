package com.example.bancoitau.com.example.bancoitau

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bancoitau.TransacaoEntity

@Dao
interface TransacaoDao {
    @Query("SELECT * FROM transacoes ORDER BY id DESC")
    suspend fun listar(): List<TransacaoEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun inserir(vararg itens: TransacaoEntity): List<Long>

    @Update
    suspend fun atualizar(item: TransacaoEntity)
    @Delete
    suspend fun deletar(item: TransacaoEntity)
    @Query("DELETE FROM transacoes") suspend fun deletarTudo()
}