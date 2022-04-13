package com.example.task15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.task15.databinding.ActivityImageBinding

private lateinit var binding: ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showImage()
    }

    private fun showImage() {
        Glide.with(binding.root)
            .load(getString(R.string.image_url))
            .into(binding.imgView)
    }
}