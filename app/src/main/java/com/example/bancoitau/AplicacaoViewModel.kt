package com.example.bancoitau

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AplicacaoViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = BancoItauDatabase.getDatabase(app).aplicacaoDao()

    val lista: StateFlow<List<AplicacaoEntity>> =
        dao.listar().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val saldo: StateFlow<Double> =
        dao.saldoFlow().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    fun adicionar(descricao: String, valor: Double) = viewModelScope.launch {
        dao.inserir(AplicacaoEntity(valor = valor, descricao = descricao))
    }

    fun atualizar(item: AplicacaoEntity) = viewModelScope.launch { dao.atualizar(item) }
    fun deletar(item: AplicacaoEntity) = viewModelScope.launch { dao.deletar(item) }
    fun limpar() = viewModelScope.launch { dao.deletarTudo() }

    companion object {
        fun factory(application: Application): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    require(modelClass.isAssignableFrom(AplicacaoViewModel::class.java))
                    return AplicacaoViewModel(application) as T
                }
            }
    }
}
