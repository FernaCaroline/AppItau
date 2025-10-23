package com.example.bancoitau.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aplicacoes")
data class AplicacaoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val valor: Double,
    val descricao: String,
    val dataMillis: Long = System.currentTimeMillis()
)
