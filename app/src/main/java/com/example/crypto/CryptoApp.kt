package com.example.crypto

import android.app.Application
import com.example.crypto.data.local.AssetDatabase
import com.example.crypto.data.local.AssetRepository
import com.example.crypto.data.local.ProductDatabase
import com.example.crypto.data.local.ProductRepository

class CryptoApp : Application() {

    private val database by lazy {
        AssetDatabase.getDatabase(this)
    }

    private val productDatabase by lazy {
        ProductDatabase.getDatabase(this)
    }

    val repository by lazy {
        AssetRepository(database.assetDao())
    }

    val productRepository by lazy {
        ProductRepository(productDatabase.productDao())
    }
}