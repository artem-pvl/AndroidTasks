package com.example.task16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.*
import com.example.task16.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.*
import timber.log.Timber
import java.io.IOException

const val URL_FLICKR =
    "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()
    private val adapter = ImageAdapter { fullSizeImageURL -> onItemClick(fullSizeImageURL) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingRcView()
        receiveImageList()
    }

    private fun receiveImageList() {
        val request = Request.Builder()
            .url(URL_FLICKR)
            .build()
        client.newCall(request).enqueue(receiveDataCallback(this))
    }

    private fun bindingRcView() {
        binding.rcView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        binding.rcView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binding.rcView.adapter = adapter
    }

    private fun receiveDataCallback(currentActivity: AppCompatActivity): Callback {
        return object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    val gson = Gson()
                    val flickrImages =
                        gson.fromJson(response.body!!.string(), FlickrImageList::class.java)

                    val imageList = mutableListOf<ImageSource>()
                    for (photo in flickrImages.photos.photo) {
                        Timber.i("Image ID: ${photo.id}, image title: ${photo.title}")

                        val imgSource = ImageSource()
                        imgSource.getFromFlickrPhoto(photo)
                        imageList.add(imgSource)
                    }

                    currentActivity.runOnUiThread {
                        adapter.addImages(imageList)
                    }
                }
            }
        }
    }

    private fun onItemClick(imgUrl: String) {
        val intent = Intent(this, FullSizeImage::class.java)
        intent.putExtra("imgUrl", imgUrl)
        startActivity(intent)
    }
}
