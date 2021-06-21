package com.example.solweather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.solweather.R
import com.example.solweather.rover_images_model.Photo
import kotlinx.android.synthetic.main.grid_view_item.view.*

class MarsAdapter(): RecyclerView.Adapter<MarsAdapter.PhotoViewHolder>() {
    inner class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Photo>(){
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.imgSrc == newItem.imgSrc
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.grid_view_item,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
       val photo = differ.currentList[position]
        holder.itemView.apply{
            Glide.with(this).load(photo.imgSrc).into(mars_image)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}