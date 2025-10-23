package com.example.bancoitau.ui.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bancoitau.data.local.AplicacaoEntity
import com.example.bancoitau.ui.BottomNavItem
import com.example.bancoitau.ui.ui.screen_feature.AplicacaoViewModel

@Composable
fun QuartaTela(
    navController: NavController,
    viewModel: AplicacaoViewModel
) {
    val lista by viewModel.lista.collectAsStateWithLifecycle()
    val saldo by viewModel.saldo.collectAsStateWithLifecycle()

    val actions = listOf(
        "+ R$10,00" to 10.0,
        "+ R$50,00" to 50.0,
        "+ R$100,00" to 100.0
    )

    var valorDigitado by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf<String?>(null) }
    var editing by remember { mutableStateOf<AplicacaoEntity?>(null) }

    Scaffold(
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomNavItem(icon = Icons.Filled.Home, label = "Início")
                    BottomNavItem(icon = Icons.Filled.Menu, label = "Extrato")
                    BottomNavItem(icon = Icons.AutoMirrored.Filled.Send, label = "Pagamentos")
                    BottomNavItem(icon = Icons.Filled.MoreVert, label = "Menu")
                }
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Voltar",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { navController.popBackStack() }
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 20.dp),
                ) {
                    Text(
                        text = "Poupança Multidata",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold, color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Quanto você deseja aplicar?",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color(0xFFFF4000), fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Saldo em conta",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold, color = Color.Black
                            )
                        )
                        Text(
                            text = "R$ %.2f".format(saldo),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold, color = Color.Black
                            )
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    items(actions) { (label, value) ->
                        Surface(
                            modifier = Modifier
                                .width(120.dp)
                                .height(80.dp)
                                .clickable {
                                    val atual = valorDigitado.replace(",", ".")
                                    val atualNum = atual.toDoubleOrNull() ?: 0.0
                                    valorDigitado = "%.2f".format(atualNum + value).replace('.', ',')
                                },
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp),
                            shadowElevation = 6.dp,
                            tonalElevation = 6.dp
                        ) {
                            Box(Modifier.fillMaxSize()) {
                                Text(
                                    text = label,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(8.dp),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.SemiBold, color = Color(0xFFFF4000)
                                    )
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = descricao,
                        onValueChange = { descricao = it; erro = null },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Descrição (ex.: Aplicação mensal)") },
                        singleLine = true,
                        leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = null, tint = Color(0xFFFF4000)) }
                    )

                    OutlinedTextField(
                        value = valorDigitado,
                        onValueChange = { novo ->
                            valorDigitado = novo.filter { it.isDigit() || it == ',' || it == '.' }
                            erro = null
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Digite o valor") },
                        singleLine = true,
                        leadingIcon = { Text("R$", fontWeight = FontWeight.Bold, color = Color.Black) }
                    )

                    if (erro != null) {
                        Text(erro!!, color = MaterialTheme.colorScheme.error)
                    }

                    Button(
                        onClick = {
                            val valor = valorDigitado.replace(',', '.').toDoubleOrNull()
                            if (descricao.isBlank()) { erro = "Informe a descrição"; return@Button }
                            if (valor == null) { erro = "Valor inválido"; return@Button }
                            viewModel.adicionar(descricao.trim(), valor)
                            valorDigitado = ""
                            descricao = ""
                            erro = null
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF4000),
                            contentColor = Color.White
                        )
                    ) { Text("Adicionar valor") }
                }
            }

            item { Spacer(Modifier.height(10.dp)) }

            // Lista de aplicações (CRUD)
            items(lista) { item ->
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { editing = item }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(item.descricao, style = MaterialTheme.typography.titleMedium)
                            Text("R$ %.2f".format(item.valor), style = MaterialTheme.typography.bodyMedium)
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedButton(onClick = { editing = item }) {
                                Icon(Icons.Filled.Edit, contentDescription = null)
                                Spacer(Modifier.width(6.dp))
                                Text("Editar")
                            }
                            OutlinedButton(
                                onClick = { viewModel.deletar(item) },
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                            ) {
                                Icon(Icons.Filled.Delete, contentDescription = null)
                                Spacer(Modifier.width(6.dp))
                                Text("Excluir")
                            }
                        }
                    }
                }
            }

            item {
                OutlinedButton(
                    onClick = { viewModel.limpar() },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Limpar todas as aplicações") }
            }
        }
    }

    // Dialog de edição/exclusão
    editing?.let { atual ->
        EditAplicacaoDialog(
            atual = atual,
            onDismiss = { editing = null },
            onUpdate = { novaDesc, novoValor ->
                viewModel.atualizar(atual.copy(descricao = novaDesc, valor = novoValor))
                editing = null
            },
            onDelete = {
                viewModel.deletar(atual)
                editing = null
            }
        )
    }
}

@Composable
private fun EditAplicacaoDialog(
    atual: AplicacaoEntity,
    onDismiss: () -> Unit,
    onUpdate: (descricao: String, valor: Double) -> Unit,
    onDelete: () -> Unit
) {
    var descricao by remember { mutableStateOf(atual.descricao) }
    var valor by remember { mutableStateOf(atual.valor.toString().replace('.', ',')) }
    var erro by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar aplicação") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = descricao,
                    onValueChange = { descricao = it; erro = null },
                    label = { Text("Descrição") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = valor,
                    onValueChange = { valor = it.filter { ch -> ch.isDigit() || ch == ',' || ch == '.' }; erro = null },
                    label = { Text("Valor (ex.: 99,90)") },
                    singleLine = true
                )
                if (erro != null) Text(erro!!, color = MaterialTheme.colorScheme.error)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val parsed = valor.replace(',', '.').toDoubleOrNull()
                if (descricao.isBlank()) { erro = "Informe a descrição"; return@TextButton }
                if (parsed == null) { erro = "Valor inválido"; return@TextButton }
                onUpdate(descricao.trim(), parsed)
            }) { Text("Salvar") }
        },
        dismissButton = {
            Row {
                TextButton(onClick = onDismiss) { Text("Cancelar") }
                Spacer(Modifier.width(8.dp))
                TextButton(
                    onClick = onDelete,
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) { Text("Excluir") }
            }
        }
    )
}
