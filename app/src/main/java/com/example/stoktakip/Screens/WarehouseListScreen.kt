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
import com.example.stoktakip.Services.Warehouse
import com.example.stoktakip.Widgets.ListAppBar
import com.example.stoktakip.Widgets.PopUp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WarehouseListScreen(
    dataViewModel: DatabaseServices.DataViewModel = viewModel(),
    navController: NavHostController
) {
    val warehouseList = dataViewModel.warehouses.value
    val warehouseName = remember { mutableStateOf("") }
    var isPopupVisible = remember{ mutableStateOf(false) }
    var selectedWarehouse by remember { mutableStateOf<Warehouse?>(null) }


    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            ListAppBar().ListAppBar(title = "Depo Listesi") {
                navController.navigate(Routes.AddWarehouse.route)
            }
        },
        content = {
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
                                selectedWarehouse = warehouse
                                isPopupVisible.value = true
//                                warehouseName.value = warehouse.warehouseName
//                                navController.navigate("${Routes.ProductInWarehouse.route}/${warehouseName.value}")
                            }) {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)) {

                                Text(text = "Depo Adı: ${ warehouse.warehouseName }")
                                Text(text = "Depo Kapasitesi: ${ warehouse.warehouseCapacity }")
                                Text(text = "Depo Konumu: ${ warehouse.warehouseLocation }")
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Depodaki Ürünleri Görüntüle >",
                                    style = TextStyle(color = Color.Blue),
                                    modifier = Modifier.clickable {
                                        warehouseName.value = warehouse.warehouseName
                                        navController.navigate("${Routes.ProductInWarehouse.route}/${warehouseName.value}")
                                    })
                            }
                        }
                    }
                )
            }
        }
    )
    if(isPopupVisible.value && selectedWarehouse != null) {
        PopUp().WarehouseDialog(
            selectedWarehouse = selectedWarehouse,
            onClose = {
                isPopupVisible.value = false
                selectedWarehouse = null
            },
            navController = navController
        )
    }
}
