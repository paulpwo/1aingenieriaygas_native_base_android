package com.pwol.flutter_app_1agas2.stepper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.pwol.flutter_app_1agas2.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Step4Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Step4Fragment : Fragment() {
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
        val root = inflater.inflate(R.layout.step4_fragment, container, false)

        val btnNew = root.findViewById(R.id.btnNew) as ImageButton
        btnNew.tag = "false"
        val btnPeriodica = root.findViewById(R.id.btnPeriodica) as ImageButton
        btnPeriodica.tag = "false"


        btnNew.setOnClickListener {
            if(btnNew.tag.toString() == "false") {
                btnNew.setBackgroundResource(R.drawable.circle_active)
                btnPeriodica.setBackgroundResource(R.drawable.circle)
                btnNew.tag = "true"
            } else {
                btnNew.setBackgroundResource(R.drawable.circle)
                btnNew.tag = "false"
            }

        }
        btnPeriodica.setOnClickListener {
            if(btnPeriodica.tag.toString() == "false") {
                btnPeriodica.setBackgroundResource(R.drawable.circle_active)
                btnNew.setBackgroundResource(R.drawable.circle)
                btnPeriodica.tag = "true"
            } else {
                btnPeriodica.setBackgroundResource(R.drawable.circle)
                btnPeriodica.tag = "false"
            }

        }



        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Step4Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Step4Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}