package com.example.task16

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.task16.databinding.ActivityFullSizeImageBinding

class FullSizeImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullSizeImageBinding

    companion object {
        const val PARAMETR_IMAGE = "imgUrl"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullSizeImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadImage()
    }

    private fun loadImage() {
        val imgUrl = intent.getStringExtra(PARAMETR_IMAGE)
        Glide.with(binding.root)
            .load(imgUrl)
            .into(binding.imgFullSizeView)
    }
}