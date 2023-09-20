package com.example.stoktakip.Widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stoktakip.Routes
import com.example.stoktakip.Services.DatabaseServices
import com.example.stoktakip.Services.Product
import com.example.stoktakip.Services.Warehouse
import com.google.gson.Gson

class PopUp {

    @Composable
    fun ProductDialog(
        selectedProduct: Product?,
        onClose: () -> Unit,
        navController: NavHostController
    ) {

        AlertDialog(
            onDismissRequest = {
                onClose()
            },
            title = {
                Text(text = "Ürün İşlemleri")
            },
            text = {
                Text(text = "Seçilen Ürün: ${selectedProduct?.productName}")
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            onClose()
                            navController.navigate("${Routes.AddProductToWarehouse.route}/${selectedProduct?.productCode}")
                        }
                    ) {
                        Text(text = "Depo Seç")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            onClose()
                            val product = Product(
                                productCode = selectedProduct?.productCode.toString(),
                                productName = selectedProduct?.productName.toString(),
                                productDescription = selectedProduct?.productDescription.toString(),
                                productPrice = selectedProduct?.productPrice.toString(),
                                productQuantity = selectedProduct?.productQuantity.toString(),
                                )
                            val productJson = Gson().toJson(product)
                            navController.navigate("${Routes.UpdateProducts.route}/$productJson")
                        }
                    ) {
                        Text(text = "Güncelle")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            onClose()
                            DatabaseServices().deleteProduct(
                                productCode = selectedProduct?.productCode.toString()
                            )
                        },
                    ) {
                        Text(text = "Sil")
                    }
                }
            }
        )
    }

    @Composable
    fun WarehouseDialog(
        selectedWarehouse: Warehouse?,
        onClose: () -> Unit,
        navController: NavHostController
    ) {

        AlertDialog(
            onDismissRequest = {
                onClose()
            },
            title = {
                Text(text = "Depo İşlemleri")
            },
            text = {
                Text(text = "Seçilen Depo: ${selectedWarehouse?.warehouseName}")
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            onClose()

                            val warehouse = Warehouse(
                                warehouseName = selectedWarehouse?.warehouseName.toString(),
                                warehouseLocation = selectedWarehouse?.warehouseLocation.toString(),
                                warehouseCapacity = selectedWarehouse?.warehouseCapacity.toString()
                            )
                            val warehouseJson = Gson().toJson(warehouse)
                            navController.navigate("${Routes.UpdateWarehouses.route}/$warehouseJson")
                        }
                    ) {
                        Text(text = "Güncelle")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            onClose()
//                            DatabaseServices().deleteProduct(
//                                productCode = selectedProduct?.productCode.toString()
//                            )
                            DatabaseServices().deleteWarehouse(
                                warehouseName = selectedWarehouse?.warehouseName.toString()
                            )
                        },
                    ) {
                        Text(text = "Sil")
                    }
                }
            }
        )
    }
}