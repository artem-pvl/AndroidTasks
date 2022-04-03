package com.example.task11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextAdapter(private val textArray: List<String>, private val callback: (String) -> Unit) :
    RecyclerView.Adapter<TextHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_element, parent, false)
        return TextHolder(view)
    }

    override fun onBindViewHolder(holder: TextHolder, position: Int) {
        return holder.bind(textArray[position], callback)
    }

    override fun getItemCount(): Int = textArray.size
}

class TextHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(text: String, callback: (String) -> Unit) {
        val txtView = view.findViewById<TextView>(R.id.textElement)
        txtView.text = text
        this.itemView.setOnClickListener { callback(text) }
    }
}
