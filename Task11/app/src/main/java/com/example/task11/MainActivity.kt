package com.example.task11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val strList = listOf("1", "2", "3", "4", "five", "some text")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val rcView = findViewById<RecyclerView>(R.id.rvStrings)
        rcView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rcView.adapter = TextAdapter(strList) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}