package com.kevin.cakeCity.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cakes")
data class Cake (
    @PrimaryKey
    val mediaId: String ="",
    val img: String? ="",
    val price: String? ="",
    val title: String? =""
        )