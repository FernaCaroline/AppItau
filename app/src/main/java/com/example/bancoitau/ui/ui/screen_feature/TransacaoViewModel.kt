package com.example.bancoitau.ui.ui.screen_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bancoitau.data.local.TransacaoEntity
import com.example.bancoitau.data.repository.TransacoesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TransacaoViewModel(
    private val repo: TransacoesRepository
) : ViewModel() {

    val lista: StateFlow<List<TransacaoEntity>> =
        repo.listar().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun adicionar(descricao: String, valor: Double, tipo: String) = viewModelScope.launch {
        repo.inserir(
            TransacaoEntity(
                valor = valor,
                descricao = descricao,
                tipo = tipo,
                dataMillis = System.currentTimeMillis()
            )
        )
    }

    fun atualizar(item: TransacaoEntity) = viewModelScope.launch { repo.atualizar(item) }

    fun deletar(item: TransacaoEntity) = viewModelScope.launch { repo.deletar(item) }

    fun limpar() = viewModelScope.launch { repo.deletarTudo() }

    class Factory(private val repo: TransacoesRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(TransacaoViewModel::class.java))
            return TransacaoViewModel(repo) as T
        }
    }
}

