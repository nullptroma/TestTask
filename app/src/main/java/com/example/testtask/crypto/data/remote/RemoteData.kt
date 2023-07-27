package com.example.testtask.crypto.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteData(
    @SerializedName("market_cap_percentage")
    val marketCapPercentage : Map<String, Double>?
)
