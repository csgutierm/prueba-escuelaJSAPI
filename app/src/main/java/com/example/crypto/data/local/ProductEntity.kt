package com.example.crypto.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val images: List<String>,
    val creationAt: String,
    val updatedAt: String,
    val categoryId: Int
)
: Serializable