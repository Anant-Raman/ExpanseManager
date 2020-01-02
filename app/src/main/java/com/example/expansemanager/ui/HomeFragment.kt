package com.example.expansemanager.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.expansemanager.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment(), OnDateSetListener {

    var cal = Calendar.getInstance()
    private lateinit var btn :Button
    private lateinit var selected_month :TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)


        selected_month = view.findViewById(R.id.selected_month)
        var mon:String = (findMonth(Calendar.getInstance().get(Calendar.MONTH))).plus(" ").plus(Calendar.getInstance().get(Calendar.YEAR))
        selected_month.setText(mon)
        Log.i("yeah",findMonth((Calendar.getInstance().get(Calendar.MONTH))))
        selected_month.setOnClickListener(View.OnClickListener {
            datepicker()
        })

        return view
    }

    private fun datepicker() {
        //cal = Calendar.getInstance()
        val picker = DatePickerDialog(
            this.requireContext(), this,
            Calendar.getInstance()[Calendar.YEAR],
            Calendar.getInstance()[Calendar.MONTH],
            Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        )
        picker.show()
    }

    override fun onDateSet(p0: DatePicker?, y: Int, m: Int, d: Int) {
        cal.set(Calendar.YEAR, y)
        cal.set(Calendar.MONTH, m)
        cal.set(Calendar.DAY_OF_MONTH, d)
        Log.i("date", cal.get(Calendar.YEAR).toString())

        updateDateInView(y, m, d)
    }

    private fun findMonth(m : Int): String
    {
        var month:String =""
        when(m+1)
        {
            1->month = "January"
            2->month = "February"
            3->month = "March"
            4->month = "April"
            5->month = "May"
            6->month = "June"
            7->month = "July"
            8->month = "August"
            9->month = "September"
            10->month = "October"
            11->month = "November"
            12->month = "December"
        }
        return month
    }

    private fun updateDateInView(y: Int, m: Int, d: Int) {

    selected_month.setText(findMonth(m).plus(" ").plus(y))
    }
}

