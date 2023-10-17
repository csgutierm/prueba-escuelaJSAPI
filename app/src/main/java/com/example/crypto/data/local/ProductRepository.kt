package com.example.crypto.data.local

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ProductRepository(private val productDao: ProductDao) {
    val allProducts: LiveData<List<ProductEntity>> = productDao.getPricedProducts()

    @WorkerThread
    fun insertProduct(product: ProductEntity) {
        productDao.insertProduct(product)
    }

    @WorkerThread
    fun updateProduct(product: ProductEntity) {
        productDao.updateProduct(product)
    }

    @WorkerThread
    fun deleteProducts() {
        productDao.deleteProducts()
    }

}