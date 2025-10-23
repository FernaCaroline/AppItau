package com.example.bancoitau.data.repository

import com.example.bancoitau.data.local.TransacaoDao
import com.example.bancoitau.data.local.TransacaoEntity
import kotlinx.coroutines.flow.Flow

class TransacoesRepository(private val dao: TransacaoDao) {

    fun listar(): Flow<List<TransacaoEntity>> = dao.listar()

    suspend fun inserir(vararg itens: TransacaoEntity) = dao.inserir(*itens)

    suspend fun atualizar(item: TransacaoEntity) = dao.atualizar(item)

    suspend fun deletar(item: TransacaoEntity) = dao.deletar(item)

    suspend fun deletarTudo() = dao.deletarTudo()
}
