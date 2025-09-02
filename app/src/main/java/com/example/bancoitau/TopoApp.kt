package com.example.bancoitau
import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bancoitau.ui.theme.BancoItauTheme

import com.example.bancoitau.MainActivity

@Composable
fun TopBarItau(nome: String = "Fernanda") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF4000)) // cor laranja Itaú
            .height(100.dp)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        // Círculo com iniciais
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color.White, shape = RoundedCornerShape(18.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = nome.take(2).uppercase(),
                color = Color(0xFFFF6600),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Nome do usuário
        Text(
            text = nome,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.weight(1f)) // empurra para direita

        // Ícones da direita
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Buscar",
            tint = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notificações",
            tint = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = "Chat",
            tint = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}
