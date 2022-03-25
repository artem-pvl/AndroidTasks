package com.example.task05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOK = findViewById<Button>(R.id.btnOK)
        val edtText = findViewById<EditText>(R.id.edtText)

        btnOK.setOnClickListener {
            val text = edtText.text.toString()
            Timber.d(text)
        }
    }
}
