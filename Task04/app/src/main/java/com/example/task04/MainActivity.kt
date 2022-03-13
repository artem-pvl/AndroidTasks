package com.example.task04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonOK = findViewById<Button>(R.id.button_ok)
        val onClick =
            { Toast.makeText(applicationContext, "Нажата OK", Toast.LENGTH_SHORT).show() }
        buttonOK.setOnClickListener { onClick() }
    }
}
