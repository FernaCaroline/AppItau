package com.example.bancoitau.ui.screen_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bancoitau.data.local.AplicacaoEntity
import com.example.bancoitau.data.repository.AplicacoesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AplicacaoViewModel(
    private val repo: AplicacoesRepository
) : ViewModel() {

    val lista: StateFlow<List<AplicacaoEntity>> =
        repo.listar().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val saldo: StateFlow<Double> =
        repo.saldo().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0
        )

    fun adicionar(descricao: String, valor: Double) = viewModelScope.launch {
        repo.inserir(AplicacaoEntity(valor = valor, descricao = descricao))
    }

    fun atualizar(item: AplicacaoEntity) = viewModelScope.launch { repo.atualizar(item) }
    fun deletar(item: AplicacaoEntity) = viewModelScope.launch { repo.deletar(item) }
    fun limpar() = viewModelScope.launch { repo.deletarTudo() }

    class Factory(private val repo: AplicacoesRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(AplicacaoViewModel::class.java))
            return AplicacaoViewModel(repo) as T
        }
    }
}
