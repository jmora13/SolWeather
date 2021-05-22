package com.example.solweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.solweather.R
import com.example.solweather.databinding.ActivityZoomedImageBinding
import kotlinx.android.synthetic.main.activity_zoomed_image.*
import kotlinx.android.synthetic.main.activity_zoomed_image.view.*
import kotlinx.android.synthetic.main.grid_view_item.view.*

class ZoomedImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityZoomedImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityZoomedImageBinding.inflate(layoutInflater)
        val view = binding.root
        val imgSrc = intent.getStringExtra("imageSrc")
        val rover = intent.getStringExtra("rover")
        val camera = intent.getStringExtra("camera")
        val date = intent.getStringExtra("date")
        if (imgSrc != null) {
            Log.d("imageSrc", imgSrc)
        }
        Glide.with(this).load(imgSrc).fitCenter()
            .into(view.mars_image_zoomed)

        binding.rover.text = rover
        binding.camera.text = camera
        binding.date.text = date

        setContentView(view)

    }
}