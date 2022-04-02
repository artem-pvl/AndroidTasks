package com.example.task10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView() {
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

        val rcView = findViewById<RecyclerView>(R.id.rcView)
        rcView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rcView.adapter = ColorAdapter(colorList)
    }
}
