package com.example.stoktakip.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stoktakip.Services.DatabaseServices

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WarehousesContainsProduct(
    dataViewModel: DatabaseServices.DataViewModel = viewModel(),
    productCode: String,
) {
    dataViewModel.productCode = productCode
    val warehouseList = dataViewModel.warehouseContainingProduct.value

    LazyColumn {
        items(
            count = warehouseList.count(),
            itemContent = {
                val warehouse = warehouseList[it]
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    elevation = 16.dp,
                    onClick = {
                    }) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) {

                        Text(text = "Depo Adı: ${ warehouse.warehouseName }")
                        Text(text = "Depo Kapasitesi: ${ warehouse.warehouseCapacity }")
                        Text(text = "Depo Konumu: ${ warehouse.warehouseLocation }")
                    }
                }
            }
        )
    }
}
