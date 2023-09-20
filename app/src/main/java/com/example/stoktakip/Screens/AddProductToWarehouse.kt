package com.example.stoktakip.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
fun AddProductToWarehouse(
    navController: NavHostController,
    productCode: String
) {

    val countTf = remember { mutableStateOf(0) }
    val warehouseNameTf = remember { mutableStateOf("") }

    Scaffold(Modifier.fillMaxSize()) {

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomTextField().LabeledOutlinedTextField(
                value = warehouseNameTf.value,
                onValueChange = {warehouseNameTf.value = it},
                label = "Depo Adını Giriniz",
                visualTransformation = VisualTransformation.None,
                keyboardType = KeyboardType.Text
            )
            Button(onClick = {
                DatabaseServices().addProductToWarehouse(
                    productCode = productCode,
                    warehouseName = warehouseNameTf.value,
                    count = 5
                )
            }) {
                Text(text = "Depoya Ekle")
            }
        }
    }
}