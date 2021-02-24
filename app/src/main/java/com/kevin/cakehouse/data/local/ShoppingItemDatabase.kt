package com.kevin.cakehouse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kevin.cakehouse.data.local.entities.ShoppingItem

@Database(
        entities = [ShoppingItem::class],
        version = 1
)
abstract class ShoppingItemDatabase: RoomDatabase() {
    abstract fun shoppingDao(): ShoppingDao
}