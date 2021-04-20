package com.example.solweather

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.solweather.rover_images_model.Photo
import com.example.solweather.ui.dashboard.MarsImageAdapter

//@BindingAdapter("imageUrl")
//fun bindImage(imgView: ImageView, imgUrl: String?) {
//    imgUrl?.let {
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//        Glide.with(imgView.context)
//            .load(imgUri)
//            .into(imgView)
//    }
//}


//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView,
//                     data: List<Photo>) {
//    val adapter = recyclerView.adapter as MarsImageAdapter
//    adapter.submitData(data.)
//}