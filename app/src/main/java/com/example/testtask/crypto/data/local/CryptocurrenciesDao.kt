package com.example.testtask.crypto.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CryptocurrenciesDao {
    @Query("SELECT * FROM cryptocurrencies")
    fun getAll(): List<LocalCryptocurrency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(cryptocurrencies: List<LocalCryptocurrency>)

    @Query("DELETE FROM cryptocurrencies")
    fun clearTable()
}