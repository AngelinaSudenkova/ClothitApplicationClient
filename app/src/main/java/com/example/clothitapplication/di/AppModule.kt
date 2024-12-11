package com.example.clothitapplication.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.clothitapplication.data.AuthRepositoryImpl
import com.example.clothitapplication.data.repository.ItemRepositoryImpl
import com.example.clothitapplication.data.repository.OutfitRepositoryImpl
import com.example.clothitapplication.data.repository.local.ClothitDatabase
import com.example.clothitapplication.data.repository.local.ItemDao
import com.example.clothitapplication.data.repository.local.OutfitDao
import com.example.clothitapplication.data.repository.remote.ClothitApiService
import com.example.clothitapplication.domain.repository.WardrobeRepository.ItemRepository
import com.example.clothitapplication.domain.repository.WardrobeRepository.OutfitRepository
import com.example.clothitapplication.domain.repository.authRepository.AuthRepository
import com.example.clothitapplication.utils.Constants
import com.example.clothitapplication.utils.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Provides
    @Singleton
    fun provideClothitApiService(): ClothitApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClothitApiService::class.java)
    }
}


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
object AppModule {

    @Provides
    @Singleton
    fun provideItemRepository(
        itemDao: ItemDao,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ItemRepository {
        return ItemRepositoryImpl(itemDao, dispatcher)
    }

    @Provides
    @Singleton
    fun provideOutfitRepository(
        outfitDao: OutfitDao,
        itemDao: ItemDao,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): OutfitRepository {
        return OutfitRepositoryImpl(outfitDao, itemDao, dispatcher)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: ClothitApiService,
        dataStoreManager: DataStoreManager
    ): AuthRepository {
        return AuthRepositoryImpl(api, dataStoreManager)
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

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("auth_prefs") }
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(dataStore: DataStore<Preferences>): DataStoreManager {
        return DataStoreManager(dataStore)
    }
}