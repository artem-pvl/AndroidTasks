package com.example.task16

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(private val images: List<ImageSource>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position], onItemClick)
    }

    override fun getItemCount(): Int = images.count()
}

class ImageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(image: ImageSource, onItemClick: (String) -> Unit) {
        val imgView = view.findViewById<ImageView>(R.id.imgView)
        imgView.contentDescription = image.description
        Glide.with(imgView.rootView)
            .load(image.urlLowRes)
            .into(imgView)

        view.setOnClickListener {
            onItemClick(image.urlHiRes)
        }
    }
}
