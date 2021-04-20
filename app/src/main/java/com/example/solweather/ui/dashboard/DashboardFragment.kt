package com.example.solweather.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.solweather.MarsService
import com.example.solweather.Resource
import com.example.solweather.adapters.MarsAdapter
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.databinding.FragmentDashboardBinding
import com.example.solweather.db.PhotoDatabase
import com.example.solweather.db.PhotosRepository
import com.example.solweather.di.Injection
import kotlinx.android.synthetic.main.fragment_dashboard.*

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

//    private val  dashboardViewModel : DashboardViewModel by lazy{
//        ViewModelProvider(this, Injection.provideViewModelFactory(activity.applicationContext )).get(DashboardViewModel::class.java)
//    }
    private lateinit var dashboardViewModel: DashboardViewModel
    private val marsAdapter = MarsImageAdapter()
    private val database = PhotoDatabase


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val repository = PhotosRepository(RetrofitInstance.imagesApi, database.getInstance(requireContext()))
        val view = binding.root
        dashboardViewModel = ViewModelProvider(requireActivity(), Injection.provideViewModelFactory(requireContext()))
            .get(DashboardViewModel::class.java)

        val layoutManager = StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.photosGrid.layoutManager = layoutManager
        binding.photosGrid.adapter = marsAdapter
        getImages()
        setHasOptionsMenu(true)

        return binding.root
    }

//    private fun hideProgressBar(){
//        paginationProgressBar.visibility = View.INVISIBLE
//    }
//    private fun showProgressBar(){
//        paginationProgressBar.visibility = View.VISIBLE
//    }



    private fun getImages(){
        lifecycleScope.launch{
            dashboardViewModel.data.collectLatest {
                marsAdapter.submitData(it)
            }
        }
    }

}