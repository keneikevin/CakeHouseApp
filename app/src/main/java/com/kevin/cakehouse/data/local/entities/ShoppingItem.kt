package com.kevin.cakehouse.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "shopping_items")
data class ShoppingItem(
        var name:String,
        var size:Int,
        var price:Float,
        @PrimaryKey(autoGenerate = true)
        val id:Int? = null
)
