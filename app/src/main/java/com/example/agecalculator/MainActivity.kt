package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvDate : TextView? = null
    private var tvMins : TextView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvDate = findViewById(R.id.tvSelectedDate)
        tvMins = findViewById(R.id.tvInMinutes)

        btnDatePicker.setOnClickListener{
            clickDatePicker()

        }


    }

    fun clickDatePicker() {
        val calenderObj = Calendar.getInstance()
        val year = calenderObj.get(Calendar.YEAR)
        val month = calenderObj.get(Calendar.MONTH)
        val day = calenderObj.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view , year , month , day ->

            Toast.makeText(this, "year $year , month ${month +1} ,  day $day",  Toast.LENGTH_LONG).show()
            val selectedDate = "$day/${month+1}/$year"
            tvDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)
            theDate?.let { val dateInMins = theDate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentInMinutes = currentDate.time / 60000
                    val differenceInMinutes = currentInMinutes - dateInMins
                    tvMins?.text = differenceInMinutes.toString()
                }



                }


        },
            year ,
            month ,
            day )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}