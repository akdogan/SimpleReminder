package com.akdogan.simplereminder.ui.newnotification

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.DateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.akdogan.simplereminder.DELAY
import com.akdogan.simplereminder.R
import com.google.android.material.textfield.TextInputLayout
import java.util.*
import android.text.format.DateFormat as TextDateFormat

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewReminderFragment : Fragment() {

    private val myViewModel: NewReminderViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //myViewModel.incrementAndPrint()
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            activity?.application?.let{
                val textInputLayout = view.findViewById<TextInputLayout>(R.id.message_body_input)
                val userMessage = textInputLayout.editText?.text.toString() ?: "NO DATA EDIT TEXT"
                myViewModel.createReminder(it, userMessage)
            }
        }
        view.findViewById<Button>(R.id.button_date_picker).setOnClickListener {
            openDatePicker(view)
        }
        view.findViewById<Button>(R.id.button_time_picker).setOnClickListener {
            openTimePicker(view)
        }
        view.findViewById<TextView>(R.id.textview_date).showDate()
        view.findViewById<TextView>(R.id.textview_time).showTime()


    }

    private fun openDatePicker(v: View){
        //hideKeyboardFromView()
        val myPickerCallback =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val cal = Calendar.getInstance()
                myViewModel.triggerTime.value?.let{
                    cal.timeInMillis = it
                    cal.set(year, month, dayOfMonth)
                    myViewModel.updateTriggerTime(cal.timeInMillis)
                    v.findViewById<TextView>(R.id.textview_date).showDate()
                }

            }
        val cal = Calendar.getInstance()
        val today = System.currentTimeMillis()
        cal.timeInMillis = myViewModel.triggerTime.value ?: today
        val picker = DatePickerDialog(
            v.context,
            myPickerCallback,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        picker.datePicker.minDate = today
        picker.show()
    }

    private fun openTimePicker(v: View){
        val myPickerCallback =
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                val cal = Calendar.getInstance()
                myViewModel.triggerTime.value?.let {
                    cal.timeInMillis = it
                    cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    cal.set(Calendar.MINUTE, minute)
                    if (cal.timeInMillis > System.currentTimeMillis() + DELAY){
                        myViewModel.updateTriggerTime(cal.timeInMillis)
                        v.findViewById<TextView>(R.id.textview_time).showTime()
                    } else {
                        Toast.makeText(context, "Must be in the future", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        val cal = Calendar.getInstance()
        val today = System.currentTimeMillis()
        cal.timeInMillis = myViewModel.triggerTime.value ?: today
        val picker = TimePickerDialog(
            v.context,
            myPickerCallback,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            TextDateFormat.is24HourFormat(context)
        )

        picker.show()

    }

    private fun TextView.showTime() {
        myViewModel.triggerTime.value?.let{
            this.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(it)
        }

    }

    private fun TextView.showDate() {
        myViewModel.triggerTime.value?.let{
            this.text = DateFormat.getDateInstance().format(it)
        }

    }
}


