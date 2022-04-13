package com.example.task15

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task15.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBtnStartClick()
    }

    private fun onBtnStartClick() {
        binding.btnStartActivity.setOnClickListener {
            val intent = Intent(this, ImageActivity::class.java)
            startActivity(intent)
        }
    }
}
