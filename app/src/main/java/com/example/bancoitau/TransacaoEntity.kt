package com.example.bancoitau

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transacoes")
data class TransacaoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descricao: String,
    val valor: Double
)