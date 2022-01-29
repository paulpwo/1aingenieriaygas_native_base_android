package com.pwol.flutter_app_1agas2.stepper

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.pwol.flutter_app_1agas2.R
import com.pwol.flutter_app_1agas2.database.departaments.DepartamentsRepository
import com.pwol.flutter_app_1agas2.database.departaments.MunicipalitiesRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Step2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Step2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.step2_fragment, container, false)
        var names: MutableList<String> = mutableListOf()
        val clearAdapter = ArrayAdapter(root.context, R.layout.list_item_groups, names)

        val menuAutocompleteDepartamento = root.findViewById(R.id.menuAutocompleteDepartamento) as AutoCompleteTextView
        val repositoryDepartaments = DepartamentsRepository(application = requireNotNull(this.activity).application)
        repositoryDepartaments.getDepartaments().observe(viewLifecycleOwner, Observer {
            var names: MutableList<String> = mutableListOf()
            it.listIterator().forEach {
                names.add(it.departament.toString())
            }
            val adapter = ArrayAdapter(root.context, R.layout.list_item_groups, names)
            menuAutocompleteDepartamento!!.setAdapter(adapter)
        })

        val menuAutocompleteMunicipio = root.findViewById(R.id.menuAutocompleteMunicipio) as AutoCompleteTextView
        val repositoryMunicipalities = MunicipalitiesRepository(application = requireNotNull(this.activity).application)
        menuAutocompleteDepartamento?.doAfterTextChanged {
            val departamento = menuAutocompleteDepartamento.text.toString()
            if(menuAutocompleteDepartamento?.getEditableText().toString().length > 0){
                menuAutocompleteMunicipio!!.setAdapter(clearAdapter)
                menuAutocompleteMunicipio!!.setText("")
                repositoryDepartaments.findDepartamentByName(departamento).observe(viewLifecycleOwner, Observer {
                    repositoryMunicipalities.getMunicipalitiesByDepartamentId(it.id_departamento!!).observe(viewLifecycleOwner, Observer {
                        var names: MutableList<String> = mutableListOf()
                        it.listIterator().forEach {
                            names.add(it.municipio.toString())
                        }
                        val adapter = ArrayAdapter(root.context, R.layout.list_item_groups, names)
                        menuAutocompleteMunicipio!!.setAdapter(adapter)
                    })

                })
            }
        }



        //val menuAutocompleteMunicipio = root.findViewById(R.id.menuAutocompleteMunicipio) as AutoCompleteTextView
        //val repository = MunicipalitiesRepository(application = requireNotNull(this.activity).application)
        //repository.findMunicipalitiesByDepartamentByName("bogota").observe(viewLifecycleOwner, Observer {
        //
        //})





        //return inflater.inflate(R.layout.step2_fragment, container, false)
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Step2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Step2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}