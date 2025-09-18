package com.example.bancoitau

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp

@Composable
fun quartaTela(navController: NavController) {

    val actions = listOf(
        Pair("+ R$10,00", Icons.Default.Create),
        Pair("+ R$50,00", Icons.Default.Search),
        Pair("+ R$100", Icons.Default.Add)
    )

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
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Saldo em conta
            item {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Voltar",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                navController.popBackStack()
                            }
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
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Quanto você deseja aplicar?",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color(0xFFFF4000),
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    )

                    {
                        Text(
                            text = "Saldo em conta",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        )
                        Text(
                            text = "R$ 586,00",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        )
                    }
                }
            }

                    item {
                        Spacer(modifier = Modifier.height(30.dp))

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        )
                        {
                            items(actions) { action ->
                                Surface(
                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(80.dp),
                                    color = Color.White,
                                    shape = RoundedCornerShape(16.dp),
                                    shadowElevation = 6.dp,
                                    tonalElevation = 6.dp
                                ) {
                                    Box {
                                        bloco2(
                                            action.first,
                                            Color.White,
                                            70,
                                            alinhamentoTexto = Alignment.BottomStart
                                        )

                                    }
                                }
                            }
                        }
                    }
            item {

                Spacer(modifier = Modifier.height(30.dp))

                var valorDigitado by remember { mutableStateOf("") }
                var saldoTotal by remember { mutableStateOf(0.0) }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = valorDigitado,
                        onValueChange = { novoValor ->
                            if (novoValor.all { it.isDigit() || it == ',' || it == '.' }) {
                                valorDigitado = novoValor
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Digite o valor") },
                        singleLine = true,
                        leadingIcon = {
                            Text("R$", fontWeight = FontWeight.Bold, color = Color.Black)
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar",
                                tint = Color(0xFFFF4000)
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

                    )

                    Button(
                        onClick = {
                            if (valorDigitado.isNotBlank()) {
                                val valorConvertido = valorDigitado.replace(",", ".").toDoubleOrNull()
                                if (valorConvertido != null) {
                                    saldoTotal += valorConvertido
                                    valorDigitado = ""
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF4000),
                            contentColor = Color.White
                        )
                    ) {

                        Spacer(modifier = Modifier.height(30.dp))

                        Text("Adicionar valor")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Saldo total:\nR$ %.2f".format(saldoTotal),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }
            }

        }
    }
}