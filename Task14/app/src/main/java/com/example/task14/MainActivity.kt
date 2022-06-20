package com.example.task14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task14.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.btnAdd.setOnClickListener {
            binding.txtView.text = getString(R.string.text_on_btn_add_click)
        }
    }
}