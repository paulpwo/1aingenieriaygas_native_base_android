package com.pwol.flutter_app_1agas2.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.pwol.flutter_app_1agas2.R

import com.pwol.flutter_app_1agas2.database.departaments.DepartamentsViewModel
import com.pwol.flutter_app_1agas2.database.departaments.MunicipalitiesRepository
import com.pwol.flutter_app_1agas2.database.departaments.MunicipalitiesViewModel
import com.pwol.flutter_app_1agas2.database.services.Service
import com.pwol.flutter_app_1agas2.database.services.ServicesViewModel
import com.pwol.flutter_app_1agas2.databinding.FragmentHomeBinding

const val FLOWER_ID = "flower id"

class HomeFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var servicesViewModel: ServicesViewModel
    private lateinit var departamentsViewModel: DepartamentsViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var textView: TextView? = null
    private val TAG = "HomeFragment"
    private lateinit var GROUP: String

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val me = this



        servicesViewModel = ViewModelProvider(this).get(ServicesViewModel::class.java)


        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val prefs = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        GROUP = prefs?.getString("group", "").toString()

        recyclerView = root.findViewById(R.id.recycler_view)
        val serviceViewModel = ViewModelProvider(this).get(ServicesViewModel::class.java)
        serviceViewModel.services.observe(viewLifecycleOwner) {
            val adapter = MyAdapter(it)
            recyclerView.adapter = adapter
            if(it.size > 0){
                textView?.visibility = View.GONE
            }
        }


        textView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
            textView?.text = "¡Parece que todo marcha bien! Ya puede agregar su primer servicio. En la zona baja y a la derecha de esta vista encontrara un Botón con el símbolo + Toque este y comenzara a crear su primera revisión. Gracias por Usar 1A Gas APP"
        })

        // TODO ONLY FOR TESTING

        val repositoryDepartaments = ViewModelProvider(this).get(DepartamentsViewModel::class.java)
        repositoryDepartaments.departaments.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "Depataments: ${it.toString()}")
        })

        val municipalitiesViewModel = ViewModelProvider(this).get(MunicipalitiesViewModel::class.java)
        municipalitiesViewModel.municipalities.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "Municipalidades: ${it.size}")
        })

        val repository = MunicipalitiesRepository(application = requireNotNull(this.activity).application)
        repository.getMunicipalitiesByDepartamentId(25).observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "Municipalidades: ${it.toString()}")
        })
        // END ONLY FOR TESTING


        //addObserver()

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
        servicesViewModel.saveService(
            Service("1234213412412",
                "NOMBRE",
                "APELLIDO",
                "Carrera 79A 334-2",
                "COMERCIAL",
                "PERIODICA",
                false,
                0,
                "",
                "",
                "",
                0,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                2,
                "$GROUP",
                false,
                "",
                "",
                "",
                "Pendiente",
                "",
                "",
                "BOGOTÁ, D.C.",
                "BOGOTÁ, D.C.",
                "",
                ""
            ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}