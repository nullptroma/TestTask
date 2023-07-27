package com.example.testtask.crypto.presentation

import com.example.testtask.crypto.domain.Cryptocurrency

data class MainScreenState(
    val cryptos : List<Cryptocurrency> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
