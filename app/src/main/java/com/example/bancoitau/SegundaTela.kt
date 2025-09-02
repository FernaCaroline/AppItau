package com.example.bancoitau
import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bancoitau.ui.theme.BancoItauTheme

import com.example.bancoitau.MainActivity


@Composable
fun segundaTela() {
    Scaffold(
        bottomBar= {
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
    )

    { innerPadding ->
        Column(
            modifier = Modifier
                //.padding(innerPadding)
                //.padding(16.dp)
                .fillMaxSize()
        ) {
            TopBarItau(nome = "Fernanda") /////////////////
            Spacer(modifier = Modifier.height(16.dp))///////////////
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            )

            {
                Text(
                    text = "Meu Itaú",
                    style = MaterialTheme.typography.bodyLarge,
                    //fontSize = 20.dp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                        .padding(horizontal = 20.dp)
                )
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Ver mais",
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(60.dp))


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 6.dp,
                    tonalElevation = 6.dp
                ) {
                    Box{
                        bloco2("Pix e \ntransferir", Color.White,120, alinhamentoTexto = Alignment.BottomStart)
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "",
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
                        bloco2("Pagar", Color.White, 120, alinhamentoTexto = Alignment.BottomStart)
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "",
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
                        bloco2("Cartão", Color.White, 120, alinhamentoTexto = Alignment.BottomStart)
                        Icon(
                            imageVector = Icons.Default.MailOutline,
                            contentDescription = "",
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp))  {

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 6.dp,
                    tonalElevation = 6.dp
                )

                {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Top Row - ícone + título
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = "Conta",
                                tint = Color(0xFF002776)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Conta Corrente",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Texto Saldo
                        Text(
                            text = "Saldo",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Valor ocultado
                        Text(
                            text = "••••••",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(24.dp))


                        // Limite da Conta
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Limite da Conta",
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
            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp))  {

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 6.dp,
                    tonalElevation = 6.dp
                )

                {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Top Row - ícone + título
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.MailOutline,
                                contentDescription = "Cartão de crédito",
                                tint = Color(0xFF002776)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Cartão de crédito",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Texto Saldo
                        Text(
                            text = "Fatura",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Valor ocultado
                        Text(
                            text = "••••••",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Limite da Conta
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Limite da cartão",
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
    selected: Boolean = false // você pode usar isso futuramente
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 8.dp)
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



