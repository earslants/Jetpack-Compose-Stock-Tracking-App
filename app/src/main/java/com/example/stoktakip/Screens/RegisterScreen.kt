package com.example.stoktakip.Screens

import com.example.stoktakip.Services.AuthViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stoktakip.R
import com.example.stoktakip.Routes
import com.example.stoktakip.Widgets.ChooseRegOrLogin
import com.example.stoktakip.Widgets.CustomTextField

@Composable
fun SignUp(navController: NavHostController) {

    val emailTf = remember { mutableStateOf("") }
    val passwordTf = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Blue, Color.White),
                    startY = 0f,
                    endY = 600f
                )
            ),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Stokyy",
                    fontSize = 24.sp,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(50.dp))

                CustomTextField().LabeledOutlinedTextField(
                    value = emailTf.value,
                    onValueChange = {emailTf.value = it},
                    label = "E-Posta",
                    visualTransformation = VisualTransformation.None,
                    keyboardType = KeyboardType.Email
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField().LabeledOutlinedTextField(
                    value = passwordTf.value,
                    onValueChange = {passwordTf.value = it},
                    label = "Şifre",
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardType = KeyboardType.Password
                )
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    onClick = {
                        if(emailTf.value != "" && passwordTf.value != "") {
                            AuthViewModel().signUp(emailTf.value, passwordTf.value)
                            navController.navigate(Routes.Login.route)
                        } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 60.dp)
                        .height(50.dp)

                ) {
                    Text(text = "Kayıt Ol")
                }
                Spacer(modifier = Modifier.height(16.dp))
                ChooseRegOrLogin().RegisterOrLogin( title = "Zaten bir hesabın var mı ? ", text = "Giriş Yap") {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.SignUp.route) {inclusive = true}
                    }
                }
            }
        }
    )
}