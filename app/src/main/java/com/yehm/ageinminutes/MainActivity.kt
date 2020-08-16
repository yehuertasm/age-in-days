package com.yehm.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var buttonDatePicker: Button
    private lateinit var textViewSelectedDate: TextView
    private lateinit var textViewAgeInMinutes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initListeners()
    }

    private fun initViews() {
        buttonDatePicker = findViewById(R.id.button_date_picker)
        textViewSelectedDate = findViewById(R.id.textView_selected_date)
        textViewAgeInMinutes = findViewById(R.id.textView_selected_date_minutes)
    }

    private fun initListeners() {
        buttonDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    textViewSelectedDate.text = selectedDate

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val date = sdf.parse(selectedDate)

                    // Divide by 86400000 to convert milliseconds to days
                    val selectedDateInDays = date!!.time.div(86400000)
                    // Divide by 86400000 to convert milliseconds to days
                    val currentDateInDays = sdf.parse(sdf.format(System.currentTimeMillis()))!!.time.div(86400000)

                    val differenceDays = currentDateInDays - selectedDateInDays

                    textViewAgeInMinutes.text = differenceDays.toString()
                },
                year,
                month,
                day
        )

        datePicker.datePicker.maxDate = Date().time
        datePicker.show()
    }
}