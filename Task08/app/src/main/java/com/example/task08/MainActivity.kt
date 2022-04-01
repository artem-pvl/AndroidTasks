package com.example.task08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtView = findViewById<TextView>(R.id.txtView)
        val btnOk = findViewById<Button>(R.id.btnOk)

        btnOk.setOnClickListener{
            txtView.text = getString(R.string.txt_view_onclick_text)
        }
    }
}