package com.example.testtask.crypto.data

import com.example.testtask.crypto.data.local.CryptocurrenciesDao
import com.example.testtask.crypto.data.local.LocalCryptocurrency
import com.example.testtask.crypto.data.remote.CryptocurrenciesApiService
import com.example.testtask.crypto.domain.Cryptocurrency
import javax.inject.Inject

class CryptocurrenciesRepository @Inject constructor(
    private val local: CryptocurrenciesDao,
    private val remote: CryptocurrenciesApiService
) {
    fun getCrypto(): List<Cryptocurrency> {
        return local.getAll().map { Cryptocurrency(it.name, it.percentage) }
    }

    suspend fun loadCrypto() {
        val data = remote.getObject().data
        val map = data.marketCapPercentage
        if(map != null)
        {
            local.clearTable()
            val list = mutableListOf<LocalCryptocurrency>()
            for (kp in map)
                list.add(LocalCryptocurrency(kp.key, kp.value))
            local.addAll(list)
        }
    }
}