package com.example.task09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtText = findViewById<EditText>(R.id.edtText)
        val chkBoxShowText = findViewById<CheckBox>(R.id.chkBoxShowText)
        val btnOk = findViewById<Button>(R.id.btnOk)
        val txtView = findViewById<TextView>(R.id.txtView)
        val prgrsCounter = findViewById<ProgressBar>(R.id.prgrsCounter)

        btnOk.setOnClickListener {
            if (chkBoxShowText.isChecked) {
                txtView.text = edtText.text
                prgrsCounter.incrementProgressBy(10)
            }
        }
    }
}