package eu.learning.ageinmin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    // objectify the text views
    var tvSelectedDate :TextView? = null
    var tvAgeInMinute : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // name of variable for button and it's ID can be same
        val btn1: Button = findViewById(R.id.buttonDatePicker)

        // give reference id to the variables
        tvSelectedDate = findViewById(R.id.tvselecteddate)
        tvAgeInMinute= findViewById(R.id.tvageinmin)

        btn1.setOnClickListener {
            // setOnClickListener is actually telling what to do once the button is pressed.
           clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val mycalendar = Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val month = mycalendar.get(Calendar.MONTH)
        // month starts from 0,i.e., january is the 0th month.
        val day = mycalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, {_, selectedyear, selectedmonth, selecteddayOfMonth ->
            Toast.makeText(this,
                "Year was $selectedyear, month was ${selectedmonth + 1}," +
                        "day of the month was ${selecteddayOfMonth}",LENGTH_LONG).show()

            val selectedDate = "$selecteddayOfMonth/${selectedmonth + 1}/$selectedyear "
            tvSelectedDate?.text = selectedDate // .text

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate.time /60000 // here .time is same as getTime function which give time in milliseconds from 1st Jan 1970 00.00.00
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())) // currentTimeMillis() give current time in milliseconds starting from 1st Jan 1970 00.00.00

            val currentdateinminute = currentDate.time /60000
            val differenceinminutes = currentdateinminute - selectedDateInMinutes
            tvAgeInMinute?.text = differenceinminutes.toString()

             },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000 // 86400000 is milliseconds in a day
        dpd.show()

    }
}