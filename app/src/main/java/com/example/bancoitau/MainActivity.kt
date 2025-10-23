package com.example.bancoitau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bancoitau.data.local.BancoItauDatabase
import com.example.bancoitau.data.repository.AplicacoesRepository
import com.example.bancoitau.data.repository.TransacoesRepository
import com.example.bancoitau.ui.ui.PrimeiraTela
import com.example.bancoitau.ui.SegundaTela
import com.example.bancoitau.ui.ui.TerceiraTela
import com.example.bancoitau.ui.ui.QuartaTela
import com.example.bancoitau.ui.ui.screen_feature.AplicacaoViewModel
import com.example.bancoitau.ui.ui.screen_feature.TransacaoViewModel
import com.example.bancoitau.ui.theme.BancoItauTheme
import com.example.bancoitau.ui.ui.PrimeiraTela

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = BancoItauDatabase.getDatabase(this)
        val aplicRepo = AplicacoesRepository(db.aplicacaoDao())
        val transRepo = TransacoesRepository(db.transacaoDao())

        setContent {
            BancoItauTheme {
                val navController = rememberNavController()

                val aplicFactory = AplicacaoViewModel.Factory(aplicRepo)
                val transFactory = TransacaoViewModel.Factory(transRepo)

                NavHost(navController = navController, startDestination = "tela1") {

                    composable("tela1") { PrimeiraTela(navController) }

                    composable("tela2") { SegundaTela(navController) }

                    composable("tela3") {
                        val transVm: TransacaoViewModel = viewModel(factory = transFactory)
                        TerceiraTela(navController, viewModel = transVm)
                    }

                    composable("tela4") {
                        val aplicVm: AplicacaoViewModel = viewModel(factory = aplicFactory)
                        QuartaTela(navController, viewModel = aplicVm)
                    }
                }
            }
        }
    }
}
