package com.example.bancoitau.ui.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bancoitau.data.local.TransacaoEntity
import com.example.bancoitau.ui.ui.screen_feature.TransacaoViewModel

@Composable
fun TerceiraTela(
    navController: NavController,
    viewModel: TransacaoViewModel
) {
    val lista by viewModel.lista.collectAsStateWithLifecycle()

    var showAddDialog by remember { mutableStateOf(false) }
    var editing by remember { mutableStateOf<TransacaoEntity?>(null) }

    val actions = listOf(
        "Controle de\ngastos" to Icons.Filled.Create,
        "Comprovantes" to Icons.Filled.Search,
        "Trazer dinheiro" to Icons.Filled.Add
    )

    Scaffold(
        bottomBar = { BottomBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Adicionar")
            }
        }
    ) { innerPadding ->

        if (showAddDialog) {
            AddTransacaoDialog(
                onDismiss = { showAddDialog = false },
                onConfirm = { descricao, valorDouble ->
                    // tipo simples padrão; ajuste se quiser escolher
                    viewModel.adicionar(descricao = descricao, valor = valorDouble, tipo = "ENTRADA")
                    showAddDialog = false
                }
            )
        }

        editing?.let { atual ->
            EditTransacaoDialog(
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Saldo em conta\n***",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Poupança",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { navController.navigate("tela4") }
                    ) {
                        Text(
                            text = "Conectar",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color(0xFFFF4000)
                            )
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Conectar",
                            tint = Color(0xFFFF4000)
                        )
                    }
                }
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    items(actions) { (title, icon) ->
                        Surface(
                            modifier = Modifier
                                .width(120.dp)
                                .height(140.dp),
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp),
                            shadowElevation = 6.dp,
                            tonalElevation = 6.dp
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(36.dp),
                                    tint = Color(0xFFFF4000)
                                )
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }

            items(lista) { item ->
                TransacaoCard(
                    descricao = item.descricao,
                    valor = item.valor,
                    tipo = item.tipo,
                ) { editing = item }
            }

            item {
                OutlinedButton(onClick = { viewModel.limpar() }) {
                    Text("Limpar transações")
                }
            }
        }
    }
}



@Composable
private fun BottomBar() {
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

@Composable
fun BottomNavItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.Black)
        Text(text = label, fontSize = 12.sp, color = Color.Black)
    }
}



@Composable
fun AddTransacaoDialog(
    onDismiss: () -> Unit,
    onConfirm: (descricao: String, valor: Double) -> Unit
) {
    var descricao by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var valorErro by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nova transação") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = { Text("Descrição") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = valor,
                    onValueChange = { novo ->
                        valor = novo.filter { it.isDigit() || it == ',' || it == '.' }
                        valorErro = null
                    },
                    label = { Text("Valor (ex.: 99,90)") },
                    singleLine = true,
                    isError = valorErro != null,
                    supportingText = { if (valorErro != null) Text(valorErro!!, color = MaterialTheme.colorScheme.error) }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val parsed = valor.replace(",", ".").toDoubleOrNull()
                if (parsed == null) {
                    valorErro = "Informe um número válido (ex.: 99.90)"
                    return@TextButton
                }
                onConfirm(descricao.trim(), parsed)
            }) { Text("Salvar") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

@Composable
fun EditTransacaoDialog(
    atual: TransacaoEntity,
    onDismiss: () -> Unit,
    onUpdate: (descricao: String, valor: Double) -> Unit,
    onDelete: () -> Unit
) {
    var descricao by remember { mutableStateOf(atual.descricao) }
    var valor by remember { mutableStateOf(atual.valor.toString().replace('.', ',')) }
    var erro by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar transação") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(value = descricao, onValueChange = { descricao = it; erro = null }, label = { Text("Descrição") }, singleLine = true)
                OutlinedTextField(
                    value = valor,
                    onValueChange = {
                        valor = it.filter { ch -> ch.isDigit() || ch == ',' || ch == '.' }
                        erro = null
                    },
                    label = { Text("Valor (ex.: 99,90)") },
                    singleLine = true,
                    isError = erro != null,
                    supportingText = { if (erro != null) Text(erro!!, color = MaterialTheme.colorScheme.error) }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val parsed = valor.replace(',', '.').toDoubleOrNull()
                if (descricao.isBlank()) { erro = "Informe a descrição"; return@TextButton }
                if (parsed == null) { erro = "Informe um número válido (ex.: 99,90)"; return@TextButton }
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

@Composable
fun TransacaoCard(
    descricao: String,
    valor: Double,
    tipo: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = null, tint = Color.Red)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = descricao, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Tipo: $tipo", style = MaterialTheme.typography.bodySmall)
                Text(text = "R$ %.2f".format(valor), style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black))
            }
        }
    }
}
