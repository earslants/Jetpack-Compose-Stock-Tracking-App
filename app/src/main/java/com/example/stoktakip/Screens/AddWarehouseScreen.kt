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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stoktakip.Services.DatabaseServices
import com.example.stoktakip.Services.Warehouse
import com.example.stoktakip.Widgets.CustomTextField

@Composable
fun AddWarehouseScreen(navController: NavHostController) {

    val capacityTf = remember { mutableStateOf("") }
    val nameTf = remember { mutableStateOf("") }
    val locationTf = remember { mutableStateOf("") }

    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CustomTextField().LabeledOutlinedTextField(
                value = nameTf.value,
                onValueChange = {nameTf.value = it},
                label = "Depo Adı",
                visualTransformation = VisualTransformation.None,
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField().LabeledOutlinedTextField(
                value = capacityTf.value,
                onValueChange = {capacityTf.value = it},
                label = "Depo Kapasitesi",
                visualTransformation = VisualTransformation.None,
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField().LabeledOutlinedTextField(
                value = locationTf.value,
                onValueChange = {locationTf.value = it},
                label = "Depo Konumu",
                visualTransformation = VisualTransformation.None,
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    if(locationTf.value != "" && capacityTf.value != "" && nameTf.value != "") {
                        DatabaseServices().addWarehouse(
                            Warehouse(
                                warehouseCapacity = capacityTf.value,
                                warehouseLocation = locationTf.value,
                                warehouseName = nameTf.value,
                            ),
                            warehouseName = nameTf.value
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp)
                    .height(50.dp)
            ) {
                Text(text = "Depo Ekle")
            }
        }
    }
}