package com.example.task15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.task15.databinding.ActivityImageBinding

const val IMG_URL = "https://bigpicture.ru/wp-content/uploads/2012/04/2184.jpg"

class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showImage()
    }

    private fun showImage() {
        Glide.with(binding.root)
            .load(IMG_URL)
            .into(binding.imgView)
    }
}