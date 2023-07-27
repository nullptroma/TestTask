package com.example.testtask

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@HiltAndroidTest
class RemoteTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun TestRemoteCrypto() {
        assert(2==2)
    }
}