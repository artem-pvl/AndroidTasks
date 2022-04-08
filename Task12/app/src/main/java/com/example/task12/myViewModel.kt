package com.example.task12

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MyViewModel : ViewModel() {
    fun logDataFromURL(urlAddress: String) {
        val connection = URL(urlAddress).openConnection() as HttpsURLConnection
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = connection.inputStream.bufferedReader().use { it.readText() }
                Timber.d("log data after read:")
                Timber.d(data)
            } finally {
                connection.disconnect()
            }
        }

    }
}