package com.pwol.flutter_app_1agas2.stepper

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pwol.flutter_app_1agas2.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Step1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Step1Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val ARG_SECTION_NUMBER = "section_number"
    private lateinit var textName: EditText
    private lateinit var textLastName: EditText
    private lateinit var textDirection: EditText

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
        val root = inflater.inflate(R.layout.step1_fragment, container, false)
        loadData(root)
        setOnChangeTexts()
        return root
    }

    fun loadData(root: View) {
        textName = root.findViewById(R.id.textName) as EditText
        textLastName = root.findViewById(R.id.textLastName) as EditText
       textDirection = root.findViewById(R.id.textDirection) as EditText

        textName.setText(prefs.getString("name", ""))
        textLastName.setText(prefs.getString("lastname", ""))
        textDirection.setText(prefs.getString("direction", ""))
    }

    fun setOnChangeTexts(){
        textName.doOnTextChanged { text, start, before, count ->
            prefs.edit().putString("name", text.toString()).apply()
        }
        textLastName.doOnTextChanged { text, start, before, count ->
            prefs.edit().putString("lastname", text.toString()).apply()
        }
        textDirection.doOnTextChanged { text, start, before, count ->
            prefs.edit().putString("direction", text.toString()).apply()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Step1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Step1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}