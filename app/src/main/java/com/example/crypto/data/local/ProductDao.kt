package com.example.crypto.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(productEntity: ProductEntity)

    @Update
    fun updateProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM product_table ORDER BY cast(price as unsigned) asc")
    fun getPricedProducts(): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM product_table WHERE id = :id")
    fun getProduct(id: String): LiveData<ProductEntity>

    @Query("DELETE FROM product_table")
    fun deleteProducts()
}