package com.example.testtask.crypto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalCryptocurrency::class], version = 1, exportSchema = false)
abstract class CryptocurrenciesDb : RoomDatabase() {
    abstract val dao : CryptocurrenciesDao
}