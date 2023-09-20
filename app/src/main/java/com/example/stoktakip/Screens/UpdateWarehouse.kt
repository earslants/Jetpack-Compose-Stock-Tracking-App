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
import com.example.stoktakip.Services.DatabaseServices
import com.example.stoktakip.Services.Product
import com.example.stoktakip.Services.Warehouse
import com.example.stoktakip.Widgets.CustomTextField

@Composable
fun UpdateWarehouse(
    warehouse: Warehouse
) {
    val warehouseNameTf = remember { mutableStateOf(warehouse.warehouseName) }
    val warehouseLocationTf = remember { mutableStateOf(warehouse.warehouseLocation) }
    val warehouseCapacityTf = remember { mutableStateOf(warehouse.warehouseCapacity) }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CustomTextField().LabeledOutlinedTextField(
                    value = warehouseNameTf.value,
                    onValueChange = {warehouseNameTf.value = it},
                    label = "Depo Adı",
                    visualTransformation = VisualTransformation.None,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField().LabeledOutlinedTextField(
                    value = warehouseLocationTf.value,
                    onValueChange = {warehouseLocationTf.value = it},
                    label = "Depo Lokasyonu",
                    visualTransformation = VisualTransformation.None,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField().LabeledOutlinedTextField(
                    value = warehouseCapacityTf.value,
                    onValueChange = {warehouseCapacityTf.value = it},
                    label = "Depo Kapasitesi",
                    visualTransformation = VisualTransformation.None,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    onClick = {
                        if(warehouseNameTf.value != "" && warehouseLocationTf.value != "" && warehouseCapacityTf.value != "") {
                            DatabaseServices().updateWarehouse(
                                Warehouse(
                                    warehouseName = warehouseNameTf.value,
                                    warehouseLocation = warehouseLocationTf.value,
                                    warehouseCapacity = warehouseCapacityTf.value
                                ),
                                warehouseName = warehouseNameTf.value
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 60.dp)
                        .height(50.dp)
                ) {
                    Text(text = "Güncelle")
                }
            }
        }
    )
}