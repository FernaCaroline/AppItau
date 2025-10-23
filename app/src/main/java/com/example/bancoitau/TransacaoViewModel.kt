package com.example.bancoitau

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransacaoViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = BancoItauDatabase.getDatabase(app).transacaoDao()

    private val _lista = MutableStateFlow<List<TransacaoEntity>>(emptyList())
    val lista: StateFlow<List<TransacaoEntity>> = _lista

    init { recarregar() }

    fun recarregar() = viewModelScope.launch { _lista.value = dao.listar() }
    fun adicionar(t: String, d: String, v: Double) = viewModelScope.launch {
        dao.inserir(TransacaoEntity(titulo = t, descricao = d, valor = v)); recarregar()
    }
    fun atualizar(item: TransacaoEntity) = viewModelScope.launch { dao.atualizar(item); recarregar() }
    fun deletar(item: TransacaoEntity) = viewModelScope.launch { dao.deletar(item); recarregar() }
    fun deletarTudo() = viewModelScope.launch { dao.deletarTudo(); recarregar() }

    companion object {
        fun factory(application: Application): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    require(modelClass.isAssignableFrom(TransacaoViewModel::class.java))
                    return TransacaoViewModel(application) as T
                }
            }
    }
}
