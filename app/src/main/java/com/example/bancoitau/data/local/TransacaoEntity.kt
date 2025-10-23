package com.example.bancoitau.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transacoes")
data class TransacaoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val descricao: String,
    val valor: Double,
    val tipo: String,
    val dataMillis: Long = System.currentTimeMillis()
)
