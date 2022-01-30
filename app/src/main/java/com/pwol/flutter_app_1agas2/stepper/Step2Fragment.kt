package com.pwol.flutter_app_1agas2.stepper

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var menuAutocompleteDepartamento: AutoCompleteTextView
    private lateinit var menuAutocompleteMunicipio: AutoCompleteTextView
    private lateinit var repositoryDepartaments: DepartamentsRepository
    private lateinit var repositoryMunicipalities: MunicipalitiesRepository
    private var  names: MutableList<String> = mutableListOf()
    private lateinit var clearAdapter: ArrayAdapter<String>
    private lateinit var textContract: EditText

    val prefs by lazy {
        requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

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

        loadData(root)
        setOnChangeListeners(root)

        return root
    }
    fun loadData(root: View) {
        menuAutocompleteDepartamento = root.findViewById(R.id.menuAutocompleteDepartamento) as AutoCompleteTextView
        repositoryDepartaments = DepartamentsRepository(application = requireNotNull(this.activity).application)
        menuAutocompleteMunicipio = root.findViewById(R.id.menuAutocompleteMunicipio) as AutoCompleteTextView
        repositoryMunicipalities = MunicipalitiesRepository(application = requireNotNull(this.activity).application)
        clearAdapter = ArrayAdapter(root.context, R.layout.list_item_groups, names)
        textContract = root.findViewById(R.id.textContract) as EditText

        prefs.getString("contract", "")?.let {
            textContract.setText(it)
        }
        menuAutocompleteDepartamento.setText(prefs.getString("departamento", ""))
        val search = menuAutocompleteDepartamento.text.toString()
        repositoryDepartaments.findDepartamentByName(search).observe(viewLifecycleOwner, Observer {
            repositoryMunicipalities.getMunicipalitiesByDepartamentId(it.id_departamento!!).observe(viewLifecycleOwner, Observer {
                var names: MutableList<String> = mutableListOf()
                it.listIterator().forEach {
                    names.add(it.municipio.toString())
                }
                val adapter = ArrayAdapter(root.context, R.layout.list_item_groups, names)
                menuAutocompleteMunicipio!!.setAdapter(adapter)
                menuAutocompleteMunicipio!!.setText(prefs.getString("municipio", ""))
            })

        })

    }
    fun setOnChangeListeners(root: View){
        repositoryDepartaments.getDepartaments().observe(viewLifecycleOwner, Observer {
            var names: MutableList<String> = mutableListOf()
            it.listIterator().forEach {
                names.add(it.departament.toString())
            }
            val adapter = ArrayAdapter(root.context, R.layout.list_item_groups, names)
            menuAutocompleteDepartamento!!.setAdapter(adapter)
        })


        menuAutocompleteDepartamento?.doAfterTextChanged {
            val departamento = menuAutocompleteDepartamento.text.toString()
            prefs.edit().putString("departamento", departamento).apply()
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
            //pageViewModel.departamento = departamento
        }


        menuAutocompleteMunicipio?.doAfterTextChanged {
            val municipio = menuAutocompleteMunicipio.text.toString()
            prefs.edit().putString("municipio", municipio).apply()
        }

        textContract.doOnTextChanged { text, start, before, count ->
            prefs.edit().putString("contract", text.toString()).apply()
        }

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