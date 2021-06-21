package com.example.solweather.ui.gallery

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.solweather.databinding.FragmentDashboardBinding
import com.example.solweather.db.PhotoDatabase
import com.example.solweather.di.Injection

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GalleryFragment : Fragment() {


    private lateinit var galleryViewModel: GalleryViewModel
    private val marsAdapter = MarsImageAdapter()
    private val database = PhotoDatabase

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDashboardBinding.inflate(inflater, container, false)

        galleryViewModel = ViewModelProvider(requireActivity(), Injection.provideViewModelFactory(requireContext()))
            .get(GalleryViewModel::class.java)

        val layoutManager = StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.photosGrid.layoutManager = layoutManager
        binding.photosGrid.adapter = marsAdapter.withLoadStateHeaderAndFooter(
                header = ImagesLoadStateAdapter { marsAdapter.retry() },
                footer = ImagesLoadStateAdapter { marsAdapter.retry() }
        )
        getImages()
        setHasOptionsMenu(true)
        return binding.root
    }



    private fun getImages(){
        lifecycleScope.launch{
            galleryViewModel.data.collectLatest {
                marsAdapter.submitData(it)
            }
        }
    }

}