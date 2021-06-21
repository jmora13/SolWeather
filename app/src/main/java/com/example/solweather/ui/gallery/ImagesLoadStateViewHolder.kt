package com.example.solweather.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.solweather.R
import com.example.solweather.databinding.ImagesLoadStateFooterViewItemBinding

class ImagesLoadStateViewHolder (
        private val binding: ImagesLoadStateFooterViewItemBinding,
        retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ImagesLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.images_load_state_footer_view_item, parent, false)
            val binding = ImagesLoadStateFooterViewItemBinding.bind(view)
            return ImagesLoadStateViewHolder(binding, retry)
        }
    }
}