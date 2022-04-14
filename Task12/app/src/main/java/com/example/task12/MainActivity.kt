package com.example.task12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import java.net.URL
import timber.log.Timber
import javax.net.ssl.HttpsURLConnection
import kotlin.concurrent.thread

const val URL_LINK =
    "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestURL(URL_LINK)
    }
}

private fun requestURL(urlLink: String) {
    thread {
        val connection = URL(urlLink).openConnection() as HttpsURLConnection
        try {
            val data = connection.inputStream.bufferedReader().use { it.readText() }
            Timber.d("Requested data:")
            Timber.d(data)
        } catch (e: NetworkOnMainThreadException) {
            Timber.d("Internet connection exception: $e")
        } finally {
            connection.disconnect()
        }
    }.run()
}
