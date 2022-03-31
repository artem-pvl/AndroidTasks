package com.example.task10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.task10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val colorList =
            listOf(
                getString(R.string.color_white),
                getString(R.string.color_red),
                getString(R.string.color_green),
                getString(R.string.color_yellow),
                getString(R.string.color_purple),
                getString(R.string.color_black),
                getString(R.string.color_fuchsin)
            )

        val adapter = ColorAdapter(colorList)
        setContentView(binding.root)

        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rcView.adapter = adapter
        }
    }
}