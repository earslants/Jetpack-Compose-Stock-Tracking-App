package com.example.stoktakip.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import com.example.stoktakip.Routes
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.stoktakip.Services.Product
import com.example.stoktakip.Services.Warehouse
import com.google.gson.Gson

@Composable
fun ScreenMain(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        // Authentication Screens
        composable(Routes.Login.route) {
            LoginPage(navController = navController)
        }
        composable(Routes.SignUp.route) {
            SignUp(navController = navController)
        }

        // Main Screen
        composable(Routes.Home.route) {
            HomePage(navController = navController)
        }
        composable(Routes.DisplayProduct.route) {
            ProductListScreen(navController = navController)
        }
        composable(Routes.DisplayWarehouse.route) {
            WarehouseListScreen(navController = navController)
        }

        // Warehouse Screens
        composable(Routes.AddWarehouse.route) {
            AddWarehouseScreen(navController = navController)
        }
        composable(Routes.DeleteWarehouse.route) {
            DeleteWarehouse(navController = navController)
        }
        composable("${Routes.ProductInWarehouse.route}/{warehouseName}",
            arguments = listOf(
                navArgument("warehouseName") {type = NavType.StringType},
            )
        ) {
            val warehouseName = it.arguments?.getString("warehouseName")!!
            ProductsInWarehouse(warehouseName = warehouseName)
        }
        composable("${Routes.WarehousesContainingProduct.route}/{productCode}",
            arguments = listOf(
                navArgument("productCode") {type = NavType.StringType},
            )
        ) {
            val productCode = it.arguments?.getString("productCode")!!
            WarehousesContainsProduct(productCode = productCode)
        }

        composable("${Routes.UpdateWarehouses.route}/{warehouse}",
            arguments = listOf(
                navArgument("warehouse") {type = NavType.StringType},
            )
        ) {
            val json = it.arguments?.getString("warehouse")
            val warehouse = Gson().fromJson(json, Warehouse::class.java)
            UpdateWarehouse(warehouse = warehouse)
        }

        // Product Screens
        composable(Routes.AddProduct.route) {
            AddProductPage(navController = navController)
        }
        composable(Routes.DeleteProduct.route) {
            DeleteProduct(navController = navController)
        }
        composable("${Routes.AddProductToWarehouse.route}/{productCode}",
            arguments = listOf(
                navArgument("productCode") {type = NavType.StringType},
            )
        ) {
            val productCode = it.arguments?.getString("productCode")!!
            AddProductToWarehouse(navController = navController ,productCode = productCode)
        }
        composable("${Routes.UpdateProducts.route}/{product}",
            arguments = listOf(
                navArgument("product") {type = NavType.StringType},
            )
        ) {
            val json = it.arguments?.getString("product")
            val product = Gson().fromJson(json, Product::class.java)
            UpdateProduct(product = product)
        }
    }
}