package com.example.stoktakip.Widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class ChooseRegOrLogin {
    @Composable
    fun RegisterOrLogin(
        title: String,
        text: String,
        onClick: () -> Unit
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.Gray,
                modifier = Modifier
                    .padding(4.dp)
            )
            Text(
                text = text,
                color = Color.Blue,
                modifier = Modifier
                    .padding(bottom = 4.dp, end = 4.dp, top = 4.dp)
                    .clickable {
                        onClick()
                    }
            )
        }
    }
}