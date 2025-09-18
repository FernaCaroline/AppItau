package com.example.bancoitau

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.compose.ui.unit.sp


data class Transacao(
    val titulo: String,
    val descricao: String,
    val valor: Double
)
@Composable
fun terceiraTela(navController: NavController) {
    val scrollState = rememberScrollState()

    val actions = listOf(
        Pair("Controle de\ngastos", Icons.Default.Create),
        Pair("Comprovantes", Icons.Default.Search),
        Pair("Trazer dinheiro", Icons.Default.Add)
    )



    var tarefas by remember {
        mutableStateOf(
            listOf(
                Transacao("Pagamento", "Pagamento de conta de luz", valor = 99.0),
                Transacao("Pix Recebido", "Transferência recebida do João", valor = 1000.0),
                Transacao("Depósito", "Depósito em conta poupança", valor = 30.0),
                Transacao("Depósito", "Compra no supermercado", valor = 285.0)
            )
        )
    }

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
                    BottomNavItem(icon = Icons.Default.Home, label = "Início")
                    BottomNavItem(icon = Icons.Default.Menu, label = "Extrato")
                    BottomNavItem(icon = Icons.Default.Send, label = "Pagamentos")
                    BottomNavItem(icon = Icons.Default.MoreVert, label = "Menu")
                }
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .height(120.dp)
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
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable{navController.navigate("tela4")}) {
                        Text(
                            text = "Conectar",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color(0xFFFF4000),
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Conectar",
                            tint = Color(0xFFFF4000)
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Cofrinhos",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Acessar",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color(0xFFFF4000),
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Acessar",
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
                    items(actions) { action ->
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
                                bloco2(
                                    action.first,
                                    Color.White,
                                    120,
                                    alinhamentoTexto = Alignment.BottomStart
                                )
                                Icon(
                                    imageVector = action.second,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(8.dp)
                                        .align(Alignment.TopStart),
                                    tint = Color(0xFFFF4000)
                                )
                            }
                        }
                    }
                }
            }

            items(tarefas) { transacao ->
                TransacaoCard(transacao)

            }
        }
    }
}

@Composable
fun TransacaoCard(transacao: Transacao) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        onClick = {
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Icon check",
                tint = Color.Red
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = transacao.titulo,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = transacao.descricao,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black
                    )
                )
                Text(
                    text = "R$ %.2f".format(transacao.valor),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black
                    )
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.Black
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}