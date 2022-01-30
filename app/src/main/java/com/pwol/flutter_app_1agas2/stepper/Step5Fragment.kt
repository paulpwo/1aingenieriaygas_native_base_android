package com.pwol.flutter_app_1agas2.stepper

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pwol.flutter_app_1agas2.R
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Step5Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Step5Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var bookingDateTime: EditText

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
        val root = inflater.inflate(R.layout.step5_fragment, container, false)

        loadData(root)
        setOnChangeTexts(root)



        return root
    }

    fun loadData(root: View) {
        bookingDateTime = root.findViewById(R.id.bookingDateTime) as EditText
        prefs.getString("bookingDateTime", "")?.let {
            bookingDateTime.setText(it)
        }
    }

    fun setOnChangeTexts(root: View) {
        bookingDateTime.setOnClickListener {
            //showDatePickerDialog(root.context)
            var value = Date()
            val cal: Calendar = Calendar.getInstance()
            cal.setTime(value)

            DatePickerDialog(
                root.context,
                OnDateSetListener { view, y, m, d ->
                    cal.set(Calendar.YEAR, y)
                    cal.set(Calendar.MONTH, m)
                    cal.set(Calendar.DAY_OF_MONTH, d)
                    val currentYear: Int = cal.get(Calendar.YEAR)
                    val currentMonth: Int = cal.get(Calendar.MONTH) + 1
                    val currentDay: Int = cal.get(Calendar.DAY_OF_MONTH)

                    val timePicker =  TimePickerDialog(
                        this.context,
                        { view, h, min ->
                            cal.set(Calendar.HOUR_OF_DAY, h)
                            cal.set(Calendar.MINUTE, min)
                            value = cal.getTime()
                            bookingDateTime.setText("${currentDay}/${currentMonth}/${currentYear} $h:$min")
                            prefs.edit().putString("bookingDateTime", "${currentDay}/${currentMonth}/${currentYear} $h:$min").apply()
                        },
                        cal.get(Calendar.HOUR_OF_DAY),
                        cal.get(Calendar.MINUTE), false
                    )
                    timePicker.show()

                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Step5Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Step5Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


