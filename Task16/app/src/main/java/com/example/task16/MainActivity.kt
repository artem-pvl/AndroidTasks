package com.example.task16

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task16.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.IOException
import java.time.LocalDateTime
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

const val URL_FLICKR =
    "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindingRcView(emptyList())
        receiveImages()
    }

    private fun receiveImages() {
        val request = createRequest()
        client.newCall(request).enqueue(responseCallback)
    }

    private val responseCallback = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Timber.e(e)
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
                val flickrImages = Gson().fromJson(response.body!!.string(), FlickrImageList::class.java)
                val imageList = flickrImages.photos.photo.map {
                    getFromFlickrPhoto(it)
                }
                val testList = flickrImages.photos.photo.map { it.toImageSource() }
                runOnUiThread {
                    bindingRcView(imageList)
                }
            }
        }
    }

    private fun createRequest() = Request.Builder()
        .url(URL_FLICKR)
        .build()

    private fun bindingRcView(imageList: List<ImageSource>) {
        binding.rcView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        binding.rcView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binding.rcView.adapter = ImageAdapter(images = imageList) {
            starFullImageActivity(it)
            showToast(it)
        }
    }

    private fun starFullImageActivity(imgUrl: String) {
        val intent = Intent(this, FullSizeImageActivity::class.java)
        intent.putExtra(FullSizeImageActivity.PARAMETR_IMAGE, imgUrl)
        startActivity(intent)
    }
}

private fun FlickrImageList.Photos.Photo.toImageSource() = ImageSource(
    urlLowRes = "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}_m.jpg",
    urlHiRes = "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}_z.jpg",
    description = title
)

fun AppCompatActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

