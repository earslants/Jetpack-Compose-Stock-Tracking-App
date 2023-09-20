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
import com.example.stoktakip.Services.Product
import com.example.stoktakip.Widgets.CustomTextField

@Composable
fun AddProductPage(navController: NavHostController) {

    val codeTf = remember { mutableStateOf("") }
    val descriptionTf = remember { mutableStateOf("") }
    val nameTf = remember { mutableStateOf("") }
    val priceTf = remember { mutableStateOf("") }
    val quantityTf = remember { mutableStateOf("") }

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomTextField().LabeledOutlinedTextField(
                value = codeTf.value,
                onValueChange = {codeTf.value = it},
                label = "Ürün Kodu",
                visualTransformation = VisualTransformation.None,
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField().LabeledOutlinedTextField(
                value = descriptionTf.value,
                onValueChange = {descriptionTf.value = it},
                label = "Ürün Açıklaması",
                visualTransformation = VisualTransformation.None,
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField().LabeledOutlinedTextField(
                value = nameTf.value,
                onValueChange = {nameTf.value = it},
                label = "Ürün Adı",
                visualTransformation = VisualTransformation.None,
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField().LabeledOutlinedTextField(
                value = priceTf.value,
                onValueChange = {priceTf.value = it},
                label = "Ürün Fiyatı",
                visualTransformation = VisualTransformation.None,
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField().LabeledOutlinedTextField(
                value = quantityTf.value,
                onValueChange = {quantityTf.value = it},
                label = "Ürün Sayısı",
                visualTransformation = VisualTransformation.None,
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    if(codeTf.value != "" && descriptionTf.value != "" && nameTf.value != "" && priceTf.value != "" && quantityTf.value != "") {
                        DatabaseServices().addProduct(
                            Product(
                                productCode = codeTf.value,
                                productDescription = descriptionTf.value,
                                productName = nameTf.value,
                                productPrice = priceTf.value,
                                productQuantity = quantityTf.value
                            ),
                            productCode = codeTf.value
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp)
                    .height(50.dp)
            ) {
                Text(text = "Ürün Ekle")
            }
        }
    }
}