package com.example.bancoitau.ui

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable

fun PrimeiraTela(navController: NavController) {
    val context = LocalContext.current

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = Color.White
                ) {
                    Bloco("Olá, Fernanda!\nCPF ***013.***-**", cor = Color.White)
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .height(70.dp)
                        .padding(8.dp)
                        .border(
                            width = 3.dp,
                            color = Color(0xFF020079),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    shadowElevation = 0.dp,
                    tonalElevation = 0.dp
                ) {
                    Bloco2("Trocar de conta", Color.White, 150, alinhamentoTexto = Alignment.Center)
                }
            }

            // Card "Acessar" - Tela 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .height(150.dp)
                        .clickable {
                            Toast.makeText(context, "Acessando sua conta :)", Toast.LENGTH_SHORT).show()
                            navController.navigate("tela2")
                        },
                    color = Color.White,
                    shadowElevation = 6.dp,
                    tonalElevation = 6.dp,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Box {
                        Bloco("Acessar", Color.White)
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
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

            Spacer(modifier = Modifier.height(10.dp))

            // Segunda linha (Pix e Pagar)
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
                        Bloco("Pix e transferir", Color.White)
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
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
                        Bloco("Pagar", Color.White)
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
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Terceira linha (Extrato e Cartões)
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
                        Bloco("Extrato", Color.White)
                        Icon(
                            imageVector = Icons.Default.Info,
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
                        Bloco("Cartões", Color.White)
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

            Spacer(modifier = Modifier.height(10.dp))

            // Quarta linha (Área Pix, iToken, Ajuda)
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
                        Bloco("Área Pix", Color.White)
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
                        Bloco("iToken", Color.White)
                        Icon(
                            imageVector = Icons.Default.AddCircle,
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
                        Bloco("Ajuda", Color.White)
                        Icon(
                            imageVector = Icons.Default.Phone,
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
}

@Composable
fun Bloco(titulo: String, cor: Color) {
    Box(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            color = cor
        ) { }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        ) {
            Text(text = titulo, style = MaterialTheme.typography.titleMedium, color = Color.Black)
        }
    }
}

@Composable
fun Bloco2(
    titulo: String,
    cor: Color,
    altura: Int,
    alinhamentoTexto: Alignment = Alignment.BottomStart
) {
    Box(
        modifier = Modifier
            .height(altura.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            color = cor
        ) { }

        Column(
            modifier = Modifier
                .align(alinhamentoTexto)
                .padding(10.dp)
        ) {
            Text(text = titulo, style = MaterialTheme.typography.titleMedium, color = Color.Black)
        }
    }
}
