package com.example.solweather.ui.notifications

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.esri.arcgisruntime.layers.WmtsLayer
import com.esri.arcgisruntime.loadable.LoadStatus
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.Basemap
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.esri.arcgisruntime.mapping.view.MapView
import com.esri.arcgisruntime.ogc.wmts.WmtsService
import com.example.solweather.R
import com.example.solweather.databinding.FragmentNotificationsBinding
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.grid_view_item.*

class NotificationsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var notificationsViewModel: NotificationsViewModel
    // objects that implement Loadable must be class fields to prevent being garbage collected before loading
    private var wmtsService: WmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/Mars_Viking_MDIM21_ClrMosaic_global_232m/1.0.0/WMTSCapabilities.xml")
    private val binding by lazy {
        FragmentNotificationsBinding.inflate(layoutInflater, container, false)
    }
    private val mapView: MapView by lazy {
        binding.mapView
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = binding.root

        setHasOptionsMenu(true)
        ArcGISRuntimeEnvironment.setApiKey("AAPK068ea680983c49f0b8e2099cc14c2568nCu382CGZ6ZsJtN72JWG2ZoptwEzdIAa5qIQdCCgB1Nu4Meami-Q-52BtV9pEEx8")
        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud6732212378,none,TRB3LNBHPBK4J9HSX010")
        // create a map with the BasemapStyle streets
        val map = ArcGISMap()

        // set the map to be displayed in the layout's MapView
        mapView.map = map
        // set the viewpoint, Viewpoint(latitude, longitude, scale)
        // display wmts data on the map
        wmtsService.addDoneLoadingListener {
            if (wmtsService.loadStatus == LoadStatus.LOADED) {
                // get service info
                val wmtsServiceInfo = wmtsService.serviceInfo
                // get the first layers id
                val layerInfos = wmtsServiceInfo.layerInfos
                // create WMTS layer from layer info
                val wmtsLayer = WmtsLayer(layerInfos[0])
                // set the basemap of the map with WMTS layer
                map.basemap = Basemap(wmtsLayer)
            }
        }
        wmtsService.loadAsync()

                    return view

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val inflater: MenuInflater = inflater
        inflater.inflate(R.menu.mosaic_menu, menu)

    }


    // set up your map here. You will call this method from onCreate()
     fun setupMap() {
        ArcGISRuntimeEnvironment.setApiKey("AAPK068ea680983c49f0b8e2099cc14c2568nCu382CGZ6ZsJtN72JWG2ZoptwEzdIAa5qIQdCCgB1Nu4Meami-Q-52BtV9pEEx8")
        // create a map with the BasemapStyle streets
        val map = ArcGISMap()

        // set the map to be displayed in the layout's MapView
        mapView.map = map
        // set the viewpoint, Viewpoint(latitude, longitude, scale)
        // display wmts data on the map
        wmtsService.addDoneLoadingListener {
            if (wmtsService.loadStatus == LoadStatus.LOADED) {
                // get service info
                val wmtsServiceInfo = wmtsService.serviceInfo
                // get the first layers id
                val layerInfos = wmtsServiceInfo.layerInfos
                // create WMTS layer from layer info
                val wmtsLayer = WmtsLayer(layerInfos[0])
                // set the basemap of the map with WMTS layer
                map.basemap = Basemap(wmtsLayer)
            }
        }
        wmtsService.loadAsync()
        //mapView.setViewpoint(Viewpoint(34.0270, -118.8050, 72000.0))
    }


    override fun onPause() {
        mapView.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.resume()
    }

    override fun onDestroy() {
        mapView.dispose()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.globalMap -> {
                wmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/Mars_Viking_MDIM21_ClrMosaic_global_232m/1.0.0/WMTSCapabilities.xml")
                setupMap()
                true
            }
            R.id.curiosity -> {
                wmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/curiosity_ctx_mosaic/1.0.0/WMTSCapabilities.xml")
                setupMap()
                true
            }
            R.id.martianEast -> {
                wmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/HRSC_Martian_east/1.0.0/WMTSCapabilities.xml")
                setupMap()
                true
            }
            R.id.MC11 -> {
                wmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/MC11E_HRMOSCO_COL/1.0.0/WMTSCapabilities.xml")
                setupMap()
                true
            }
            R.id.spirit -> {
                wmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/spirit_hirise_mosaic/1.0.0/WMTSCapabilities.xml")
                setupMap()
                true
            }
            R.id.opportunity -> {
                wmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/opportunity_hirise_mosaic/1.0.0/WMTSCapabilities.xml")
                setupMap()
                true
            }
            R.id.phoenix -> {
                wmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/phoenix_hirise_mosaic/1.0.0/WMTSCapabilities.xml")
                setupMap()
                true
            }
            R.id.sojourner -> {
                wmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/sojourner_hirise_mosaic/1.0.0/WMTSCapabilities.xml")
                setupMap()
                true
            }
            R.id.thermalEmissionSpectrum -> {
                wmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/Mars_MGS_TES_Albedo_mosaic_global_7410m/1.0.0/WMTSCapabilities.xml")
                setupMap()
                true
            }
            R.id.marsOrbiterLaserAltimeter -> {
                wmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/Mars_MGS_MOLA_ClrShade_merge_global_463m/1.0.0/WMTSCapabilities.xml")
                setupMap()
                true
            }
            R.id.marsOrbiterCamera -> {
                wmtsService = WmtsService("https://api.nasa.gov/mars-wmts/catalog/msss_atlas_simp_clon/1.0.0/WMTSCapabilities.xml")
                setupMap()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}