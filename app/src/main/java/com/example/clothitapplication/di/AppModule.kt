package com.example.clothitapplication.di

import android.content.Context
import androidx.room.Room
import com.example.clothitapplication.data.repository.local.ClothitDatabase
import com.example.clothitapplication.data.repository.local.ItemDao
import com.example.clothitapplication.data.repository.local.OutfitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ClothitDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ClothitDatabase::class.java,
            "clothit_database"
        ).build()
    }

    @Provides
    fun provideItemDao(database: ClothitDatabase): ItemDao {
        return database.itemDao()
    }

    @Provides
    fun provideOutfitDao(database: ClothitDatabase): OutfitDao {
        return database.outfitDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {

    @IoDispatcher
    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}