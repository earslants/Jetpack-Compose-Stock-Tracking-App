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
import com.example.stoktakip.Widgets.CustomTextField

@Composable
fun GetProductInfo() {

    val productCodeTf = remember { mutableStateOf("") }
    val productCode = remember { mutableStateOf("") }
    val productName = remember { mutableStateOf("") }
    val productDescription = remember { mutableStateOf("") }
    val productPrice = remember { mutableStateOf("") }
    val productQuantity = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Ürün Kodu : ${productCode.value}")
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Ürün Adı : ${productName.value}")
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Ürün Açıklaması : ${productDescription.value}")
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Ürün Fiyatı : ${productPrice.value}")
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Ürün Sayısı : ${productQuantity.value}")
                Spacer(modifier = Modifier.height(50.dp))

                CustomTextField().LabeledOutlinedTextField(
                    value = productCodeTf.value,
                    onValueChange = {productCodeTf.value = it},
                    label = "Ürün Kodunu Giriniz",
                    visualTransformation = VisualTransformation.None,
                    keyboardType = KeyboardType.Text
                )
                Button(onClick = {
                    if(productCodeTf.value != ""){
                        DatabaseServices().getSpecProduct(
                            productCodeTf.value,
                            onSuccess = { product ->
                                if (product != null) {
                                    productCode.value = product.productCode
                                    productName.value = product.productName
                                    productDescription.value = product.productDescription
                                    productPrice.value = product.productPrice
                                    productQuantity.value = product.productQuantity
                                } else {
                                    println("Ürün Bulunamadı.")
                                }
                            },
                            onError = { exception ->
                                println("Hata: $exception")
                            }
                        )
                    }
                }) {
                    Text(text = "Sorgula")
                }
            }
        }
    )
}