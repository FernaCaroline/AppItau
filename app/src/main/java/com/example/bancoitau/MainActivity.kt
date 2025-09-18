package com.example.bancoitau

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.bancoitau.ui.theme.BancoItauTheme

//O card "Acessar" dará acesso à segunda tela.
//Na segunda tela, o botão inferior ''Extrato", leva à terceira tela.
//Na terceira tela, o botão "Conectar" - da Poupança -, leva à quarta tela.
//Na quarta tela, é possível adicionar manualmente o valor desejado, e este será exibido quando clicar em "Adicionar".

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BancoItauTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "tela1") {
                    composable("tela1") { primeiraTela(navController) }
                    composable("tela2") { segundaTela(navController) }
                    composable("tela3") { terceiraTela(navController) }
                    composable("tela4") { quartaTela(navController) }
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun primeiraTela(navController: androidx.navigation.NavController) {

    val context = LocalContext.current

    Scaffold {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = Color.White
                    )
                {
                    bloco("Olá, Fernanda!\nCPF ***013.***-**", cor = Color.White)
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .height(height = 70.dp)
                        .padding(8.dp)
                        .border(width = 3.dp,
                            color = Color(0xFF020079),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    shadowElevation = 0.dp,
                    tonalElevation = 0.dp)
                {
                    bloco2("Trocar de conta", Color.White, 150, alinhamentoTexto = Alignment.Center)
                }

            }


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp))  {

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
                        bloco("Acessar", Color.White)
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
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

            Spacer(modifier = Modifier.height(10.dp))

            // Segunda linha com dois cards
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
                    Box {
                        bloco("Pix e transferir", Color.White)
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
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
                        bloco("Pagar", Color.White)
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
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Terceira linha com dois cards
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
                    Box {
                        bloco("Extrato", Color.White)
                        Icon(
                            imageVector = Icons.Default.Info,
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
                            bloco("Cartões", Color.White)
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

            Spacer(modifier = Modifier.height(10.dp))

            // Quarta linha com três cards
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
                        bloco("Area Pix", Color.White)
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
                        bloco("iToken", Color.White)
                        Icon(
                            imageVector = Icons.Default.AddCircle,
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
                        bloco("Ajuda", Color.White)
                        Icon(
                            imageVector = Icons.Default.Phone,
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
        }
    }
}

@Composable
fun bloco(titulo: String, cor: Color) {
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
        ) {
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun bloco2(
    titulo: String,
    cor: Color,
    altura: Int,
    alinhamentoTexto: Alignment = Alignment.BottomStart){
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
        ) {
        }

        Column(
            modifier = Modifier
                .align(alinhamentoTexto)
                .padding(10.dp)
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }
    }
}


