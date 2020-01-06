package com.example.expansemanager.ui.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.expansemanager.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_add_transaction_dialog.view.*
import java.util.*


class HomeFragment : Fragment(), OnDateSetListener {

    var cal = Calendar.getInstance()
    private lateinit var btn :Button
    private lateinit var selected_month :TextView
    private lateinit var mBtnAddTransaction: ImageButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        initViews(view)
        return view
    }

    private fun initViews(view : View)
    {
        selected_month = view.findViewById(R.id.selected_month)
        var mon:String = (findMonth(Calendar.getInstance().get(Calendar.MONTH))).plus(" ").plus(Calendar.getInstance().get(Calendar.YEAR))
        selected_month.setText(mon)
        selected_month.setOnClickListener(View.OnClickListener {
            datepicker()
        })
        mBtnAddTransaction = view.findViewById(R.id.btn_add_transaction)
        mBtnAddTransaction.setOnClickListener(View.OnClickListener {

            val view_bottomsheet: View = layoutInflater.inflate(R.layout.fragment_add_transaction_dialog, null)

            val dialog = BottomSheetDialog(activity!!, R.style.BottomSheetDialog)
            dialog.setContentView(view_bottomsheet)
            dialog.show()

            var amountInput: EditText = view_bottomsheet.findViewById(R.id.amt_input)
            var amtVal: Editable? = amountInput.text

            var descriptionInput: EditText = view_bottomsheet.findViewById(R.id.transaction_desc)
            var descStr: Editable = descriptionInput.text

            var isDebit: Boolean?

            var btnDebit: TextView = view_bottomsheet.findViewById(R.id.btn_sel_debit)
            var btnCredit: TextView = view_bottomsheet.findViewById(R.id.btn_sel_credit)
            var btnTransactionSubmit: Button = view_bottomsheet.findViewById(R.id.btn_amt_submit)
            var categoryInput: TextView = view_bottomsheet.findViewById(R.id.category_val)
            var categoryStr: String= categoryInput.text.toString()
            var dateVal: TextView = view_bottomsheet.findViewById(R.id.date_val)
            var dateStr: String = dateVal.text.toString()

            btnDebit.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorDark)

            btnDebit.setOnClickListener(View.OnClickListener {
                isDebit = true
                Log.i("debit", isDebit.toString())
                btnDebit.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorDark)
                btnCredit.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
//                btnDebit.setBackgroundResource(R.drawable.selected)
//                btnCredit.setBackgroundResource(R.drawable.unselected)
            })
            btnCredit.setOnClickListener(View.OnClickListener {
                isDebit = false
                Log.i("debit", isDebit.toString())
                btnCredit.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorDark)
                btnDebit.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
//                btnCredit.setBackgroundResource(R.drawable.selected)
//                btnDebit.setBackgroundResource(R.drawable.unselected)
            })

            btnTransactionSubmit.setOnClickListener(View.OnClickListener {
                Log.i("Print", amtVal.toString()+" "+descStr+" "+categoryStr+" "+dateStr)
            })

        })

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

