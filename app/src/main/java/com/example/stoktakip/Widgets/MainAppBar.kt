package com.example.stoktakip.Widgets

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.stoktakip.Services.AuthViewModel

class MainAppBar {
    @SuppressLint("NotConstructor")
    @Composable
    fun MainAppBar(title: String, onClick: () -> Unit) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                )
            },
            actions = {
                IconButton(
                    onClick = {
                        AuthViewModel().signOut()
                        onClick()
                    }
                ) {
                    Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Çıkış Yap")
                }
            },
            backgroundColor = Color.White
        )
    }
}