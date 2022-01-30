package com.pwol.flutter_app_1agas2.stepper

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pwol.flutter_app_1agas2.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Step6Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Step6Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var contrato: TextView
    private lateinit var name: TextView
    private lateinit var lastname: TextView
    private lateinit var direction: TextView
    private lateinit var type_client: TextView
    private lateinit var type_revision: TextView
    private lateinit var booking: TextView


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
        val root = inflater.inflate(R.layout.step6_fragment, container, false)

        loadData(root)

        return root
    }
    fun loadData(root: View) {
        contrato = root.findViewById(R.id.contrato)
        name = root.findViewById(R.id.name)
        lastname = root.findViewById(R.id.lastname)
        direction = root.findViewById(R.id.direction)
        type_client = root.findViewById(R.id.type_client)
        type_revision = root.findViewById(R.id.type_revision)
        booking = root.findViewById(R.id.booking)

        contrato.text = prefs.getString("contract", "")
        name.text = prefs.getString("name", "")
        lastname.text = prefs.getString("lastname", "")
        direction.text = prefs.getString("direction", "")
        type_client.text = prefs.getString("tipo_cliente", "")
        type_revision.text = prefs.getString("tipo_servicio", "")
        booking.text = prefs.getString("bookingDateTime", "")

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Step6Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Step6Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}