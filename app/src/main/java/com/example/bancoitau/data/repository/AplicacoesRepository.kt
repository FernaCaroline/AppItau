package com.example.bancoitau.data.repository

import com.example.bancoitau.data.local.AplicacaoDao
import com.example.bancoitau.data.local.AplicacaoEntity
import kotlinx.coroutines.flow.Flow

class AplicacoesRepository(private val dao: AplicacaoDao) {

    fun listar(): Flow<List<AplicacaoEntity>> = dao.listar()

    fun saldo(): Flow<Double> = dao.saldoFlow()

    suspend fun inserir(vararg itens: AplicacaoEntity) = dao.inserir(*itens)

    suspend fun atualizar(item: AplicacaoEntity) = dao.atualizar(item)

    suspend fun deletar(item: AplicacaoEntity) = dao.deletar(item)

    suspend fun deletarTudo() = dao.deletarTudo()
}
