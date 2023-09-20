package com.example.stoktakip.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.stoktakip.Routes
import com.example.stoktakip.Services.DatabaseServices
import com.example.stoktakip.Services.Product
import com.example.stoktakip.Widgets.ListAppBar
import com.example.stoktakip.Widgets.PopUp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductListScreen(
    dataViewModel: DatabaseServices.DataViewModel = viewModel(),
    navController: NavHostController
) {
    val productList = dataViewModel.products.value
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    var isPopupVisible = remember{ mutableStateOf(false) }

    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            ListAppBar().ListAppBar(title = "Ürün Listesi") {
                navController.navigate(Routes.AddProduct.route)
            }
        },
        content = {
            LazyColumn {
                items(
                    count = productList.count(),
                    itemContent = {
                        val product = productList[it]
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                            elevation = 16.dp,
                            onClick = {
                                selectedProduct = product
                                isPopupVisible.value = true
//                                navController.navigate("${Routes.WarehousesContainingProduct.route}/${selectedProduct?.productCode}")
                            }
                        ) {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)) {
                                Text(text = "Ürün Kodu: ${ product.productCode }")
                                Text(text = "Ürün Adı: ${ product.productName }")
                                Text(text = "Ürün Açıklaması: ${ product.productDescription }")
                                Text(text = "Ürün Fiyatı: ${ product.productPrice }")
                                Text(text = "Stok Adet: ${ product.productQuantity }")
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Bulunduğu Depoları Görüntüle >",
                                    style = TextStyle(color = Color.Blue),
                                    modifier = Modifier.clickable {
                                        navController.navigate("${Routes.WarehousesContainingProduct.route}/${product.productCode}")
                                    })
                            }
                        }
                    }
                )
            }
        }

    )
    if(isPopupVisible.value && selectedProduct != null) {
        PopUp().ProductDialog(
            selectedProduct = selectedProduct,
            onClose = {
                isPopupVisible.value = false
                selectedProduct = null
            },
            navController = navController
        )
    }
}

