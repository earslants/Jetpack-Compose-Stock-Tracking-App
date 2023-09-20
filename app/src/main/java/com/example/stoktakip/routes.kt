package com.example.stoktakip

import com.squareup.okhttp.Route

sealed class Routes(val route: String) {

    // Authentication Routes
    object Login : Routes("Login")
    object SignUp: Routes("SignUp")

    // Main Routes
    object Home: Routes("Home")
    object DisplayProduct: Routes("DisplayProduct")
    object DisplayWarehouse: Routes("DisplayWarehouse")

    // Product Routes
    object AddProduct: Routes("AddProduct")
    object DeleteProduct: Routes("DeleteProduct")
    object AddProductToWarehouse: Routes("AddProductToWarehouse")
    object UpdateProducts: Routes("UpdateProducts")

    // Warehouse Routes
    object AddWarehouse: Routes("AddWarehouse")
    object DeleteWarehouse: Routes("DeleteWarehouse")
    object ProductInWarehouse: Routes("ProductInWarehouse")
    object WarehousesContainingProduct: Routes("WarehouseContainingProduct")
    object UpdateWarehouses: Routes("UpdateWarehouses")

}