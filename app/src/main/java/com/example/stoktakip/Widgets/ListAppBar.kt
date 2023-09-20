package com.example.stoktakip.Widgets

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class ListAppBar {
    @SuppressLint("NotConstructor")
    @Composable
    fun ListAppBar(title: String, onClick: () -> Unit) {
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
                        onClick()
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Ekle")
                }
            },
            backgroundColor = Color.White
        )
    }
}