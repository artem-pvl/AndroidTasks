package com.example.task16

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task16.databinding.ImageItemBinding

class ImageAdapter(
    private val images: List<ImageSource>,
    private val onItemClick: (String) -> Unit
) :
    RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position], onItemClick)
    }

    override fun getItemCount(): Int = images.count()
}

class ImageViewHolder(private val binding: ImageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(image: ImageSource, onItemClick: (String) -> Unit) {
        initImgView(image, onItemClick)
    }

    private fun initImgView(
        image: ImageSource,
        onItemClick: (String) -> Unit
    ) {
        fillImgView(image)
        setImgViewReactions(image, onItemClick)
    }

    private fun fillImgView(image: ImageSource) {
        binding.imgView.contentDescription = image.description
        Glide.with(binding.imgView.rootView)
            .load(image.urlLowRes)
            .into(binding.imgView)
    }

    private fun setImgViewReactions(
        image: ImageSource,
        onItemClick: (String) -> Unit,
    ) {
        binding.imgView.setOnClickListener {
            onItemClick(image.urlHiRes)
        }
    }
}
