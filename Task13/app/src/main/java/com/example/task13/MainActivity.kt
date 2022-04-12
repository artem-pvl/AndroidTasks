package com.example.task13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val request = Request.Builder()
            .url(getString(R.string.url_link))
            .build()

        client.newCall(request).enqueue(getDataCallback())
    }
}

private fun getDataCallback(): Callback {
    return object : Callback {
        override fun onFailure(call: Call, e: okio.IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
                if (response.isSuccessful) {
                    for ((name, value) in response.headers) {
                        Timber.i("Response header $name: $value")
                    }
                    Timber.i("All body from response: \n${response.body!!.string()}")
                } else {
                    Timber.d("OkHTTP IO error")
                }
            }
        }
    }
}
