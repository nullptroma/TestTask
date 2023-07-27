package com.example.testtask.crypto.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cryptocurrencies")
data class LocalCryptocurrency(
    @PrimaryKey @ColumnInfo(name = "r_name") val name: String,
    @ColumnInfo(name = "r_percentage") val percentage: Double
)
