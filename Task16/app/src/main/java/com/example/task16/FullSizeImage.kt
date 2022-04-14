package com.example.task16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.task16.databinding.ActivityFullSizeImageBinding

class FullSizeImage : AppCompatActivity() {
    private lateinit var binding: ActivityFullSizeImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullSizeImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadImage()
    }

    private fun loadImage() {
        val imgUrl = intent.getStringExtra("imgUrl")
        Glide.with(binding.root)
            .load(imgUrl)
            .into(binding.imgFullSizeView)
    }
}