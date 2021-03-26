package com.kevin.cakeCity.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kevin.cakeCity.R
import com.kevin.cakeCity.data.local.ShoppingDao
import com.kevin.cakeCity.data.local.ShoppingItemDatabase
import com.kevin.cakeCity.other.Constants.DATABASE_NAME
import com.kevin.cakeCity.other.Constants.ENCRYPTED_SHARED_PREF_NAME
import com.kevin.cakeCity.repositories.DefaultShoppingRepository
import com.kevin.cakeCity.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideEncryptedSharedPreferences(
            @ApplicationContext context: Context
    ): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
        return EncryptedSharedPreferences.create(
                context,
                ENCRYPTED_SHARED_PREF_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Singleton
    @Provides
    fun provideShoppingItemDataBase(
            @ApplicationContext context: Context
    )= Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShoppingDao(
            database:ShoppingItemDatabase
    ) = database.shoppingDao()


    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
            dao: ShoppingDao
    ) = DefaultShoppingRepository(dao) as ShoppingRepository

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )
}



























