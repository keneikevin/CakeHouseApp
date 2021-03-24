package com.kevin.cakeCity.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cake_items")
data class Cake(
        var title: String? = null,
        var price: String? = null,
        val img: String? = null,
        @PrimaryKey(autoGenerate = true)
    val id:Int? = null
)
