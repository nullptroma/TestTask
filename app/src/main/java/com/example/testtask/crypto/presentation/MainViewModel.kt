package com.example.testtask.crypto.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.crypto.data.CryptocurrenciesRepository
import com.example.testtask.di.IoDispatcher
import com.example.testtask.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: CryptocurrenciesRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {
    val state: State<MainScreenState>
        get() = _state
    private val _state = mutableStateOf(MainScreenState())

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message, isLoading = false
        )
    }

    fun getCryptos() {
        if(_state.value.isLoading)
            return
        viewModelScope.launch(errorHandler + ioDispatcher) {
            val res = repo.getCrypto()
            withContext(mainDispatcher) {
                _state.value = _state.value.copy(
                    cryptos = res
                )
            }
        }
    }

    fun loadCryptos() {
        if(_state.value.isLoading)
            return
        _state.value = _state.value.copy(
            isLoading = true
        )
        viewModelScope.launch(errorHandler + ioDispatcher) {
            repo.loadCrypto()
            withContext(mainDispatcher) {
                _state.value = _state.value.copy(
                    isLoading = false
                )
                getCryptos()

            }
        }
    }
}