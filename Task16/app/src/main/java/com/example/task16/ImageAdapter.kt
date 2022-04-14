package com.example.task16

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.bumptech.glide.Glide


class ImageAdapter(private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<ImageViewHolder>() {

    private val imageSourceList: SortedList<ImageSource> = SortedList(
        ImageSource::class.java,
        sortedListAdapterCallback()
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageSourceList[position], onItemClick)
    }

    override fun getItemCount(): Int = imageSourceList.size()

    private fun sortedListAdapterCallback() =
        object : SortedListAdapterCallback<ImageSource>(this) {
            override fun compare(
                o1: ImageSource,
                o2: ImageSource
            ): Int {
                return when {
                    o1.urlLowRes > o2.urlLowRes -> 1
                    o1.urlLowRes < o2.urlLowRes -> -1
                    else -> 0
                }
            }

            override fun areContentsTheSame(
                oldItem: ImageSource,
                newItem: ImageSource
            ): Boolean = oldItem.hashCode() == newItem.hashCode()

            override fun areItemsTheSame(
                item1: ImageSource,
                item2: ImageSource
            ): Boolean = item1 == item2
        }

    fun addImages(imageList: MutableList<ImageSource>) {
        for (image in imageList) {
            imageSourceList.add(image)
        }
    }
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
