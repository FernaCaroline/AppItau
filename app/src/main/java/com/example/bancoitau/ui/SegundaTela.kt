package com.example.bancoitau.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SegundaTela(navController: NavController) {

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
                    BottomNavItem(
                        icon = Icons.Default.Home,
                        label = "Início",
                        selected = true,
                        onClick = {
                            navController.navigate("tela1") {
                                popUpTo("tela1") { inclusive = true }
                            }
                        }
                    )
                    BottomNavItem(
                        icon = Icons.Default.Menu,
                        label = "Extrato",
                        onClick = {
                            navController.navigate("tela3") {
                                popUpTo("tela3") { inclusive = true }
                            }
                        }
                    )
                    BottomNavItem(icon = Icons.AutoMirrored.Filled.Send, label = "Pagamentos")
                    BottomNavItem(icon = Icons.Default.MoreVert, label = "Menu")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TopBarItau(nome = "Fernanda")
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Meu Itaú",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp)
                )
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Ver mais",
                    tint = Color.Black,
                    modifier = Modifier.padding(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Linha de atalhos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 6.dp,
                    tonalElevation = 6.dp
                ) {
                    Box {
                        Bloco2("Pix e \ntransferir", Color.White, 120, alinhamentoTexto = Alignment.BottomStart)
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(8.dp)
                                .align(Alignment.TopStart),
                            tint = Color(0xFFFF4000)
                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 6.dp,
                    tonalElevation = 6.dp
                ) {
                    Box {
                        Bloco2("Pagar", Color.White, 120, alinhamentoTexto = Alignment.BottomStart)
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(8.dp)
                                .align(Alignment.TopStart),
                            tint = Color(0xFFFF4000)
                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 6.dp,
                    tonalElevation = 6.dp
                ) {
                    Box {
                        Bloco2("Cartão", Color.White, 120, alinhamentoTexto = Alignment.BottomStart)
                        Icon(
                            imageVector = Icons.Default.MailOutline,
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

            Spacer(modifier = Modifier.height(24.dp))

            // Cartão: Conta Corrente
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 6.dp,
                    tonalElevation = 6.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = "Conta",
                                tint = Color(0xFF002776)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Conta Corrente",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Saldo", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "••••••", style = MaterialTheme.typography.bodyLarge, color = Color.Black)

                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Limite da conta",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Ver mais",
                                tint = Color.Gray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Cartão: Cartão de crédito
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 6.dp,
                    tonalElevation = 6.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.MailOutline,
                                contentDescription = "Cartão de crédito",
                                tint = Color(0xFF002776)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Cartão de crédito",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Fatura", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "••••••", style = MaterialTheme.typography.bodyLarge, color = Color.Black)

                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Limite do cartão",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Ver mais",
                                tint = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                onClick()
                Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
            }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) Color(0xFFFF4000) else Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (selected) Color(0xFFFF4000) else Color.Black
        )
    }
}
