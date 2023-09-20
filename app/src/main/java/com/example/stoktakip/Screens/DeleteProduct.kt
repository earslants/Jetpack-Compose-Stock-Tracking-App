package com.example.stoktakip.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import com.example.stoktakip.Services.DatabaseServices
import com.example.stoktakip.Widgets.CustomTextField

@Composable
fun DeleteProduct(navController: NavHostController) {

    val productCodeTf = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CustomTextField().LabeledOutlinedTextField(
                    value = productCodeTf.value,
                    onValueChange = {productCodeTf.value = it},
                    label = "Ürün Kodunu Giriniz",
                    visualTransformation = VisualTransformation.None,
                    keyboardType = KeyboardType.Text
                )
                Button(onClick = {
                    DatabaseServices().deleteProduct(productCodeTf.value)
                }) {
                    Text(text = "Ürünü Sil")
                }
            }
        }
    )
}