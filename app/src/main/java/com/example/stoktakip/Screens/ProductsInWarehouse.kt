package com.example.stoktakip.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stoktakip.Services.DatabaseServices

@Composable
fun ProductsInWarehouse(
    dataViewModel: DatabaseServices.DataViewModel = viewModel(),
    warehouseName: String
) {
    dataViewModel.selectedWarehouseId = warehouseName
    val productList = dataViewModel.productInWh.value

    LazyColumn {
        items(
            count = productList.count(),
            itemContent = {
                val product = productList[it]
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    elevation = 16.dp,
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) {
                        Text(text = "Ürün Kodu: ${ product.productCode }")
                        Text(text = "Ürün Adı: ${ product.productName }")
                        Text(text = "Ürün Açıklaması: ${ product.productDescription }")
                        Text(text = "Ürün Fiyatı: ${ product.productPrice }")
                        Text(text = "Stok Adet: ${ product.productQuantity }")
                    }
                }
            }
        )
    }
}
