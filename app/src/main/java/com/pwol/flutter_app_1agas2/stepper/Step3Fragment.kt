package com.pwol.flutter_app_1agas2.stepper

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pwol.flutter_app_1agas2.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Step3Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Step3Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var btnResidecial: ImageButton
    private lateinit var btnComercial: ImageButton
    private lateinit var btnIndustrial: ImageButton

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
        val root = inflater.inflate(R.layout.step3_fragment, container, false)
        loadData(root)
        setOnChangeTexts()
        return root
    }

    fun loadData(root: View){
        btnResidecial = root.findViewById(R.id.btnResidecial) as ImageButton
        btnResidecial.tag = "false"
        btnComercial = root.findViewById(R.id.btnComercial) as ImageButton
        btnComercial.tag = "false"
        btnIndustrial = root.findViewById(R.id.btnIndustrial) as ImageButton
        btnIndustrial.tag = "false"

        prefs.getString("tipo_cliente", "")?.let {
            if(it == "Residencial"){
                btnResidecial.setBackgroundResource(R.drawable.circle_active)
                btnResidecial.tag = "true"
            }
            if(it == "Comercial"){
                btnComercial.setBackgroundResource(R.drawable.circle_active)
                btnComercial.tag = "true"
            }
            if(it == "Industrial"){
                btnIndustrial.setBackgroundResource(R.drawable.circle_active)
                btnIndustrial.tag = "true"
            }
        }
    }

    fun setOnChangeTexts(){

        btnResidecial.setOnClickListener {
            if(btnResidecial.tag.toString() == "false") {
                btnResidecial.setBackgroundResource(R.drawable.circle_active)
                btnComercial.setBackgroundResource(R.drawable.circle)
                btnIndustrial.setBackgroundResource(R.drawable.circle)
                btnResidecial.tag = "true"
                prefs.edit().putString("tipo_cliente", "Residencial").apply()
            } else {
                btnResidecial.setBackgroundResource(R.drawable.circle)
                btnResidecial.tag = "false"
                prefs.edit().putString("tipo_cliente", "").apply()
            }
        }
        btnComercial.setOnClickListener {
            if(btnComercial.tag.toString() == "false") {
                btnComercial.setBackgroundResource(R.drawable.circle_active)
                btnResidecial.setBackgroundResource(R.drawable.circle)
                btnIndustrial.setBackgroundResource(R.drawable.circle)
                btnComercial.tag = "true"
                prefs.edit().putString("tipo_cliente", "Comercial").apply()
            } else {
                btnComercial.setBackgroundResource(R.drawable.circle)
                btnComercial.tag = "false"
                prefs.edit().putString("tipo_cliente", "").apply()
            }

        }
        btnIndustrial.setOnClickListener {
            if(btnIndustrial.tag.toString() == "false") {
                btnIndustrial.setBackgroundResource(R.drawable.circle_active)
                btnResidecial.setBackgroundResource(R.drawable.circle)
                btnComercial.setBackgroundResource(R.drawable.circle)
                btnIndustrial.tag = "true"
                prefs.edit().putString("tipo_cliente", "Industrial").apply()
            } else {
                btnIndustrial.setBackgroundResource(R.drawable.circle)
                btnIndustrial.tag = "false"
                prefs.edit().putString("tipo_cliente", "").apply()
            }

        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Step3Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Step3Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}