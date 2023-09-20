package com.example.stoktakip.Services

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Suppress("UNCHECKED_CAST")
class DatabaseServices {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val currentMail = auth.currentUser?.email.toString()

    private val productsCollection: CollectionReference = db.collection("users").document(currentMail).collection("products")

    private val warehousesCollection: CollectionReference = db.collection("users").document(currentMail).collection("warehouses")

    // Ürün ekle
    fun addProduct(product: Product, productCode: String): Task<Void> {
        val newProductRef: DocumentReference = productsCollection.document(productCode)
        return newProductRef.set(product)
    }

    // Depo eklem
    fun addWarehouse(warehouse: Warehouse, warehouseName: String): Task<Void> {
        val newWarehouseRef: DocumentReference = warehousesCollection.document(warehouseName)
        return newWarehouseRef.set(warehouse)
    }

    // Ürünü güncelle
    fun updateProduct(product: Product, productCode: String): Task<Void> {
        val productRef: DocumentReference = productsCollection.document(productCode)
        return productRef.set(product)
    }

    // Depoyu güncelle
    fun updateWarehouse(warehouse: Warehouse, warehouseName: String): Task<Void> {
        val warehouseRef: DocumentReference = warehousesCollection.document(warehouseName)
        return warehouseRef.set(warehouse)
    }

    // Ürün sil
    fun deleteProduct(productCode: String): Task<Void> {
        val productRef: DocumentReference = productsCollection.document(productCode)
        return productRef.delete()
    }

    // Depo sil
    fun deleteWarehouse(warehouseName: String): Task<Void> {
        val warehouseRef: DocumentReference = warehousesCollection.document(warehouseName)
        return warehouseRef.delete()
    }

    fun addProductToWarehouse(warehouseName: String, productCode: String, count: Int): Task<Void> {

        val warehouseRef: DocumentReference = warehousesCollection.document(warehouseName)

        return db.runTransaction { transaction ->
            val warehouseDocSnapshot: DocumentSnapshot = transaction.get(warehouseRef)

            if (!warehouseDocSnapshot.exists()) {
                throw Exception("Depo bulunamadı: $warehouseName")
            }

            val productsMap: MutableMap<String, Any> = warehouseDocSnapshot.get("products") as? MutableMap<String, Any>
                ?: mutableMapOf()

            productsMap[productCode] = count
            transaction.update(warehouseRef, "products", productsMap)

            null
        }
    }

    // Ürün kodu girilerek ürün durumunu sorgular
    fun getSpecProduct(productCode: String, onSuccess: (Product?) -> Unit, onError: (Exception) -> Unit) {
        val productRef: DocumentReference = productsCollection.document(productCode)

        productRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val productData = documentSnapshot.data
                    if (productData != null) {
                        val product = Product(
                            productCode = productCode,
                            productDescription = productData["productDescription"] as String,
                            productName = productData["productName"] as String,
                            productPrice = productData["productPrice"] as String,
                            productQuantity = productData["productQuantity"] as String
                        )
                        onSuccess(product)
                    } else {
                        // Ürün bilgileri eksik
                        onSuccess(null)
                    }
                } else {
                    // Ürün koduna sahip doküman bulunamadı
                    onSuccess(null)
                }
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }

    suspend fun getProductsInWarehousesFromFirestore(warehouseId: String): List<Product> {
        val productListInWarehouses = mutableListOf<Product>()

        try {
            val warehouseDoc = warehousesCollection.document(warehouseId).get().await()

            val productsMap = warehouseDoc.get("products") as? Map<String, Any>
            if (productsMap != null) {
                for (productCode in productsMap.keys) {
                    val productCountInWarehouse = productsMap[productCode] as? Long
                    if (productCountInWarehouse != null) {
                        val productDoc = productsCollection.document(productCode).get().await()
                        val product = productDoc.toObject(Product::class.java)
                        if (product != null) {
                            product.productQuantity = productCountInWarehouse.toString()
                            productListInWarehouses.add(product)
                        }
                    }
                }
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getProductsInWarehousesFromFirestore: $e")
        }

        return productListInWarehouses
    }

    class DataViewModel : ViewModel() {
        val products = mutableStateOf<List<Product>>(emptyList())
        val warehouses = mutableStateOf<List<Warehouse>>(emptyList())
        val productInWh = mutableStateOf<List<Product>>(emptyList())
        val warehouseContainingProduct = mutableStateOf<List<Warehouse>>(emptyList())

        var selectedWarehouseId: String? = null
        var productCode: String? = null

        init {
            getData()
        }

        private fun getData() {
            viewModelScope.launch {
                products.value = DatabaseServices().getProductFromFirestore()
                warehouses.value = DatabaseServices().getWarehouseFromFirestore()
//                productInWh.value = DatabaseServices().getProductsInWarehousesFromFirestore()
                selectedWarehouseId?.let { warehouseId ->
                    productInWh.value = DatabaseServices().getProductsInWarehousesFromFirestore(warehouseId)
                }

                productCode?.let { code ->
                    warehouseContainingProduct.value = DatabaseServices().findWarehousesContainingProduct(code)
                }
            }
        }
    }

    suspend fun getProductFromFirestore(): List<Product> {
        val productList = mutableListOf<Product>()

        try {
            val querySnapshot = productsCollection.get().await()
            for (document in querySnapshot.documents) {
                val product = document.toObject(Product::class.java)
                if (product != null) {
                    productList.add(product)
                }
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getProductFromFirestore: $e")
        }

        return productList
    }

    suspend fun getWarehouseFromFirestore(): List<Warehouse> {
        val warehouseList = mutableListOf<Warehouse>()

        try {
            val querySnapshot = warehousesCollection.get().await()
            for (document in querySnapshot.documents) {
                val warehouse = document.toObject(Warehouse::class.java)
                if (warehouse != null) {
                    warehouseList.add(warehouse)
                }
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getWarehouseFromFirestore: $e")
        }

        return warehouseList
    }

    suspend fun findWarehousesContainingProduct(productCode: String): List<Warehouse> {
        val warehousesContainingProduct = mutableListOf<Warehouse>()

        try {
            val warehousesQuerySnapshot = warehousesCollection
                .whereGreaterThanOrEqualTo("products.$productCode", 1) // Ürün miktarı 1 veya daha fazla olanları al
                .get()
                .await()

            for (warehouseDoc in warehousesQuerySnapshot.documents) {
                val warehouse = warehouseDoc.toObject(Warehouse::class.java)
                if (warehouse != null) {
                    warehousesContainingProduct.add(warehouse)
                }
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "findWarehousesContainingProduct: $e")
        }

        return warehousesContainingProduct
    }

}
