package com.example.testtask.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.testtask.crypto.data.local.CryptocurrenciesDb
import com.example.testtask.crypto.data.local.CryptocurrenciesDao
import com.example.testtask.crypto.data.remote.CryptocurrenciesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CryptocurrenciesModule {
    @Provides
    fun provideRoomDao(database: CryptocurrenciesDb): CryptocurrenciesDao {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext appContext: Context
    ): CryptocurrenciesDb {
        return Room.databaseBuilder(
            appContext, CryptocurrenciesDb::class.java, "restaurants_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.coingecko.com/api/v3/")
            .build()
    }

    @Provides
    fun provideRetrofitApi(retrofit: Retrofit): CryptocurrenciesApiService {
        Log.d("Hello", "Where")
        return retrofit.create(CryptocurrenciesApiService::class.java)
    }
}