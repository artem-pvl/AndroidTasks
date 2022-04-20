package com.example.task16

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.task16.databinding.ActivityFullSizeImageBinding

class FullSizeImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullSizeImageBinding

    companion object {
        const val PARAMETER_IMAGE = "imgUrl"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullSizeImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showImage()
    }

    private fun showImage() {
        val imgUrl = intent.getStringExtra(PARAMETER_IMAGE)
        Glide.with(binding.root)
            .load(imgUrl)
            .into(binding.imgFullSizeView)
    }
}
