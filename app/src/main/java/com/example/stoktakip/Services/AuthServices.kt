package com.example.stoktakip.Services

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.stoktakip.Routes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val email = auth.currentUser?.email.toString()
    private val usersCollection: CollectionReference = firestore.collection("users")

    fun signIn(email: String, password: String, navController: NavHostController) {
        viewModelScope.launch (Dispatchers.IO) {

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    Log.d(TAG, "Inside_OnCompleteListener")
                    Log.d(TAG, "is Successful = ${it.isSuccessful}")
                    if(it.isSuccessful) {
                        navController.navigate(Routes.Home.route)
                    }
                }
                .addOnFailureListener{
                    Log.d(TAG, "Inside_OnFailureListener")
                    Log.d(TAG, "Exception = ${it.message}")
                    Log.d(TAG, "is Successful = ${it.localizedMessage}")
                }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    Log.d(TAG, "Inside_OnCompleteListener")
                    Log.d(TAG, "is Successful = ${it.isSuccessful}")
                    val userData = hashMapOf(
                        "email" to email,
                    )
                    usersCollection.document(email).set(userData)
                }
                .addOnFailureListener{
                    Log.d(TAG, "Inside_OnFailureListener")
                    Log.d(TAG, "Exception = ${it.message}")
                    Log.d(TAG, "is Successful = ${it.localizedMessage}")
                }
        }
    }

    fun signOut() {
        auth.signOut()
    }
}
