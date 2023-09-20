package com.example.stoktakip.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stoktakip.Routes
import com.example.stoktakip.Services.AuthViewModel
import com.example.stoktakip.R
import com.example.stoktakip.Widgets.CustomButton
import com.example.stoktakip.Widgets.MainAppBar

@Composable
fun HomePage(navController: NavHostController) {

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainAppBar().MainAppBar(
                title = "Ana Menü",
                onClick = {
                    AuthViewModel().signOut()
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Home.route) {inclusive = true}
                    }
                }
            )
        },
        content = {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.factory),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.height(300.dp)
                )
                Spacer(modifier = Modifier.height(50.dp))
                CustomButton().CustomButton(text = "Ürünleri Listele", onClick = {navController.navigate(Routes.DisplayProduct.route)})
                Spacer(modifier = Modifier.height(15.dp))
                CustomButton().CustomButton(text = "Depoları Listele", onClick = {navController.navigate(Routes.DisplayWarehouse.route)})
            }
        }
    )
}
