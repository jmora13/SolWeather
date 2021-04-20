package com.example.solweather.ui.dashboard

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.solweather.R
import com.example.solweather.databinding.GridViewItemBinding
import com.example.solweather.rover_images_model.Photo
import kotlinx.android.synthetic.main.grid_view_item.view.*


class MarsImageAdapter : PagingDataAdapter<Photo, MarsImageAdapter.PhotoViewHolder>(PHOTO_COMPARATOR){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.itemView.apply{
            Glide.with(this).load(photo?.imgSrc)
                .into(mars_image)
        }
    }

    class PhotoViewHolder(private var binding:
                          GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(photo : Photo){
            binding.photo = photo
            binding.executePendingBindings()
        }
    }

    companion object {
        private val PHOTO_COMPARATOR= object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                // Id is unique.
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }
}