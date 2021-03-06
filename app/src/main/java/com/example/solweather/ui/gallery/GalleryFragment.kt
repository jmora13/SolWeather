package com.example.solweather.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.dataStore
import com.example.solweather.databinding.FragmentGalleryBinding
import com.example.solweather.db.PhotoDatabase
import com.example.solweather.di.Injection
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GalleryFragment : Fragment() {


    private lateinit var galleryViewModel: GalleryViewModel
    private val marsAdapter = MarsImageAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentGalleryBinding.inflate(inflater, container, false)

        galleryViewModel = ViewModelProvider(requireActivity(), Injection.provideViewModelFactory(requireContext()))
            .get(GalleryViewModel::class.java)

        val layoutManager = StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.photosGrid.layoutManager = layoutManager
        binding.photosGrid.adapter = marsAdapter.withLoadStateHeaderAndFooter(
                header = ImagesLoadStateAdapter { marsAdapter.retry() },
                footer = ImagesLoadStateAdapter { marsAdapter.retry() }
        )
        lifecycleScope.launch {
            try{
                val response = galleryViewModel.getMaxDate()
                save("maxDate", response.toString())
            } finally {
                galleryViewModel.data.collectLatest {
                    binding.spinner.visibility = View.GONE
                    marsAdapter.submitData(it)
                }
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }



    suspend fun save(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context?.dataStore?.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

}