package com.example.task14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task14.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBtnAddClick()
    }

    private fun onBtnAddClick() {
        binding.btnAdd.setOnClickListener {
            binding.txtView.text = getString(R.string.text_on_btn_add_click)
        }
    }
}