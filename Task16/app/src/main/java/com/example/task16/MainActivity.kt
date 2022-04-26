package com.example.task16

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task16.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.IOException
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRcView(emptyList())
        fillRcView()
    }

    private fun initRcView(images: List<ImageSource>) {
        binding.rcView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        binding.rcView.adapter = ImageAdapter(images = images) {
            clickOnElement(it)
        }
    }

    private fun clickOnElement(imgURL: String) {
        starFullSizeImageActivity(imgURL)
        showToast(imgURL)
    }

    private fun starFullSizeImageActivity(imgURL: String) {
        val intent = Intent(this, FullSizeImageActivity::class.java)
        intent.putExtra(FullSizeImageActivity.PARAMETER_IMAGE, imgURL)
        startActivity(intent)
    }

    private val client = OkHttpClient()

    private fun fillRcView() {
        val request = createRequest()
        client.newCall(request).enqueue(getImagesAndFill)
    }

    private fun createRequest() = Request.Builder()
        .url(URL_FLICKR)
        .build()

    private val getImagesAndFill = object : Callback {
        override fun onResponse(call: Call, response: Response) {
            response.use {
                val flickrImages = deSerializeFlickr(response)
                val images = toImageSourceList(flickrImages)
                fillRcView(images)
            }
        }

        private fun deSerializeFlickr(response: Response) =
            Gson().fromJson(response.body!!.string(), FlickrImageList::class.java)

        private fun toImageSourceList(flickrImages: FlickrImageList) =
            flickrImages.photos.photo.map {
                it.toImageSource()
            }

        private fun fillRcView(images: List<ImageSource>) {
            runOnUiThread {
                initRcView(images)
            }
        }

        override fun onFailure(call: Call, e: IOException) {
            Timber.e("Failure $e with $call")
        }
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
