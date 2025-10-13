package com.example.bancoitau

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext



@Composable
fun terceiraTela(navController: NavController) {
    val context = LocalContext.current
    val vm: TransacaoViewModel = viewModel(
        factory = TransacaoViewModel.factory(context.applicationContext as Application)
    )

    val lista by vm.lista.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }

// edição
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
                onConfirm = { titulo, descricao, valorDouble ->
                    vm.adicionar(titulo, descricao, valorDouble)
                    showAddDialog = false
                }
            )
        }

// Editar / Excluir
        editing?.let { atual ->
            EditTransacaoDialog(
                atual = atual,
                onDismiss = { editing = null },
                onUpdate = { novoTitulo, novaDesc, novoValor ->
                    vm.atualizar(atual.copy(titulo = novoTitulo, descricao = novaDesc, valor = novoValor))
                    editing = null
                },
                onDelete = {
                    vm.deletar(atual)
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
            // Saldo em conta
            item {
                Text(
                    text = "Saldo em conta\n***",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            // Poupança
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
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { navController.navigate("tela4") }
                    ) {
                        Text(
                            text = "Conectar",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color(0xFFFF4000),
                                fontWeight = FontWeight.SemiBold
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

            // Ações
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
                            Box {
                                // Se você tiver o bloco2, mantenha; caso não, remova
                                // bloco2(title, Color.White, 120, alinhamentoTexto = Alignment.BottomStart)
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
            }

            // Agora vem da base
            items(items = lista) { item ->
                TransacaoCard(
                    titulo = item.titulo,
                    descricao = item.descricao,
                    valor = item.valor,
                    onClick = { editing = item }   // abre o dialog de edição
                )
            }

            // Botão para limpar tudo (opcional)
            item {
                OutlinedButton(onClick = { vm.deletarTudo() }) {
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

// ====== SUPORTE ======

data class Transacao(
    val titulo: String,
    val descricao: String,
    val valor: Double
)

@Composable
fun AddTransacaoDialog(
    onDismiss: () -> Unit,
    onConfirm: (titulo: String, descricao: String, valor: Double) -> Unit
) {
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var valorErro by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nova transação") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = { Text("Descrição") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = valor,
                    onValueChange = { novo ->
                        // mantém só dígitos, vírgula e ponto
                        valor = novo.filter { it.isDigit() || it == ',' || it == '.' }
                        valorErro = null
                    },
                    label = { Text("Valor (ex.: 99,90)") },
                    singleLine = true,
                    isError = valorErro != null,
                    supportingText = {
                        if (valorErro != null) {
                            Text(valorErro!!, color = MaterialTheme.colorScheme.error)
                        }
                    }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                // aceita vírgula e ponto
                val parsed = valor.replace(",", ".").toDoubleOrNull()
                if (parsed == null) {
                    valorErro = "Informe um número válido (ex.: 99.90)"
                    return@TextButton
                }
                if (titulo.isBlank()) {
                    valorErro = null
                    return@TextButton
                }
                onConfirm(titulo.trim(), descricao.trim(), parsed)
            }) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}


@Composable
fun TransacaoCard(
    titulo: String,
    descricao: String,
    valor: Double,
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
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Icon check",
                tint = Color.Red
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(text = descricao, style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black))
                Text(
                    text = "R$ %.2f".format(valor),
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black)
                )
            }
        }
    }
}

@Composable
fun EditTransacaoDialog(
    atual: TransacaoEntity,
    onDismiss: () -> Unit,
    onUpdate: (titulo: String, descricao: String, valor: Double) -> Unit,
    onDelete: () -> Unit
) {
    var titulo by remember { mutableStateOf(atual.titulo) }
    var descricao by remember { mutableStateOf(atual.descricao) }
    var valor by remember { mutableStateOf(atual.valor.toString().replace('.', ',')) }
    var erro by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar transação") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it; erro = null },
                    label = { Text("Título") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = descricao,
                    onValueChange = { descricao = it; erro = null },
                    label = { Text("Descrição") },
                    singleLine = true
                )
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
                if (titulo.isBlank()) { erro = "Informe um título"; return@TextButton }
                if (parsed == null) { erro = "Informe um número válido (ex.: 99,90)"; return@TextButton }
                onUpdate(titulo.trim(), descricao.trim(), parsed)
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
fun BottomNavItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.Black)
        Text(text = label, fontSize = 12.sp, color = Color.Black)
    }
}
