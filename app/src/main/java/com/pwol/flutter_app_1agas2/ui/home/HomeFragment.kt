package com.pwol.flutter_app_1agas2.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.pwol.flutter_app_1agas2.database.departaments.DepartamentsViewModel
import com.pwol.flutter_app_1agas2.database.services.Service
import com.pwol.flutter_app_1agas2.database.services.FaqsViewModel
import com.pwol.flutter_app_1agas2.database.services.ServicesViewModel
import com.pwol.flutter_app_1agas2.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var servicesViewModel: ServicesViewModel
    private lateinit var departamentsViewModel: DepartamentsViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var textView: TextView? = null
    private val TAG = "HomeFragment"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val me = this
        servicesViewModel = ViewModelProvider(this).get(ServicesViewModel::class.java)
        departamentsViewModel = ViewModelProvider(this).get(DepartamentsViewModel::class.java)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
            textView?.text = "¡Parece que todo marcha bien! Ya puede agregar su primer servicio. En la zona baja y a la derecha de esta vista encontrara un Botón con el símbolo + Toque este y comenzara a crear su primera revisión. Gracias por Usar 1A Gas APP"
        })


        departamentsViewModel.departaments.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "Departamentos: ${it.size}")
        })

        addObserver()

        //addService()
        //addService()
        //addService()
        //addService()
        //addService()
        //addService()
        //addService()

        val receiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent != null) {
                    val str = intent.getStringExtra("refresh_database")
                    if(str == "refresh_database"){
                        addObserver()
                    }
                }
            }
        }

        val lbm = LocalBroadcastManager.getInstance(binding.root.context)
        lbm.registerReceiver(receiver, IntentFilter("refresh_database"))

        return root
    }
    fun addObserver() {

        val observer = Observer<List<Service>> { services ->
            if (services != null) {
                var text = ""
                for (ser in services) {
                    text += ser.id.toString() + "\n"
                }
                textView?.text = text
            }
        }
        servicesViewModel.services.observe(viewLifecycleOwner, observer)
    }

    private fun addService() {
        servicesViewModel.saveService(Service("service demo"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}