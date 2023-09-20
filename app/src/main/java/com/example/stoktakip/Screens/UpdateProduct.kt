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
import com.example.stoktakip.Widgets.CustomTextField

@Composable
fun UpdateProduct(
    product: Product
) {
    
    val productCodeTf = remember { mutableStateOf(product.productCode) }
    val productNameTf = remember { mutableStateOf(product.productName) }
    val productDescriptionTf = remember { mutableStateOf(product.productDescription) }
    val productPriceTf = remember { mutableStateOf(product.productPrice) }
    val productQuantityTf = remember { mutableStateOf(product.productQuantity) }
    
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
                    value = productCodeTf.value,
                    onValueChange = {productCodeTf.value = it},
                    label = "Ürün Kodu",
                    visualTransformation = VisualTransformation.None,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField().LabeledOutlinedTextField(
                    value = productDescriptionTf.value,
                    onValueChange = {productDescriptionTf.value = it},
                    label = "Ürün Açıklaması",
                    visualTransformation = VisualTransformation.None,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField().LabeledOutlinedTextField(
                    value = productNameTf.value,
                    onValueChange = {productNameTf.value = it},
                    label = "Ürün Adı",
                    visualTransformation = VisualTransformation.None,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField().LabeledOutlinedTextField(
                    value = productPriceTf.value,
                    onValueChange = {productPriceTf.value = it},
                    label = "Ürün Fiyatı",
                    visualTransformation = VisualTransformation.None,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField().LabeledOutlinedTextField(
                    value = productQuantityTf.value,
                    onValueChange = {productQuantityTf.value = it},
                    label = "Ürün Sayısı",
                    visualTransformation = VisualTransformation.None,
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    onClick = {
                        if(productCodeTf.value != "" && productDescriptionTf.value != "" && productNameTf.value != "" && productPriceTf.value != "" && productQuantityTf.value != "") {
                            DatabaseServices().updateProduct(
                                Product(
                                    productCode = productCodeTf.value,
                                    productDescription = productDescriptionTf.value,
                                    productName = productNameTf.value,
                                    productPrice = productPriceTf.value,
                                    productQuantity = productQuantityTf.value
                                ),
                                productCode = productCodeTf.value
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