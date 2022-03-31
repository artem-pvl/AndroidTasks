package com.example.task10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task10.databinding.ColorItemBinding

class ColorAdapter(val colorList: List<String>) : RecyclerView.Adapter<ColorAdapter.ColorHolder>() {
    class ColorHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ColorItemBinding.bind(item)
        fun bind(color: String) {
            binding.txtColor.text = color
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.color_item, parent, false)
        return ColorHolder(view)
    }

    override fun onBindViewHolder(holder: ColorHolder, position: Int) {
        holder.bind(colorList[position])
    }

    override fun getItemCount(): Int {
        return colorList.size
    }
}
