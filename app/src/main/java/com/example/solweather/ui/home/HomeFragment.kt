package com.example.solweather.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.solweather.R
import com.example.solweather.adapters.DataFragmentAdapter
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.truncate

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.mars.animation = AnimationUtils.loadAnimation(context, R.anim.float_anim)
        lifecycleScope.launchWhenCreated {
            val response = try {
                homeViewModel.getWeatherResponse()
            } catch (e: IOException) {
                Log.d("IOEXCEPTION", e.message.toString())
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.d("HTTPEXCEPTION", e.stackTrace.toString())
                return@launchWhenCreated
            }
            binding.spinner.visibility = View.GONE
            val maxTempF = response?.maxTemp?.let { convertCelciusToFahrenheit(it.toDouble()) }
            val minTempF = response?.minTemp?.let { convertCelciusToFahrenheit(it.toDouble()) }
            val maxGtsTempF = response?.maxGtsTemp?.let { convertCelciusToFahrenheit(it.toDouble()) }
            val minGtsTempF = response?.minGtsTemp?.let { convertCelciusToFahrenheit(it.toDouble()) }
            if(response != null){
                binding.maxTemp.text = String.format("%.1f",maxTempF) + "??F"
                binding.minTemp.text = String.format("%.1f",minTempF)  + "??F"
                binding.weather.text = response?.atmoOpacity
                binding.seasonValue.text = response?.season
                binding.pressureValue.text = response?.pressure.toString() + " Mb"
                binding.groundTempValue.text = (maxGtsTempF.toString() + "??/" + minGtsTempF.toString()!! + "??")
                binding.sunriseSunsetValue.text = (response?.sunrise.toString() + "/" + response?.sunset.toString()!!)
                binding.uvValue.text =  response?.localUvIrradianceIndex.toString()
            }
        }
        return view
    }

    private fun convertCelciusToFahrenheit(celsius: Double): Double {
        return truncate(celsius * 9 / 5 + 32)
    }


}