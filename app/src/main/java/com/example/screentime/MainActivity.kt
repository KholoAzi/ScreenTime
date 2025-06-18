package com.example.screentime

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etDate: EditText
    private lateinit var etMorning: EditText
    private lateinit var etAfternoon: EditText
    private lateinit var etNotes: EditText
    private lateinit var btnSave: Button
    private lateinit var btnClear: Button
    private lateinit var btnDetails: Button

    // Parallel arrays to store weekly data
    private val dates = mutableListOf<String>()
    private val morningTimes = mutableListOf<Int>()
    private val afternoonTimes = mutableListOf<Int>()
    private val notes = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        etDate = findViewById(R.id.etDate)
        etMorning = findViewById(R.id.etMorning)
        etAfternoon = findViewById(R.id.etAfternoon)
        etNotes = findViewById(R.id.etNotes)

        btnSave = findViewById(R.id.btnSave)
        btnClear = findViewById(R.id.btnClear)
        btnDetails = findViewById(R.id.btnDetails)

        // Save Button logic
        btnSave.setOnClickListener {
            val date = etDate.text.toString()
            val morning = etMorning.text.toString()
            val afternoon = etAfternoon.text.toString()
            val note = etNotes.text.toString()

            if (date.isEmpty() || morning.isEmpty() || afternoon.isEmpty()) {
                Toast.makeText(this, "All fields except notes are required.", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    dates.add(date)
                    morningTimes.add(morning.toInt())
                    afternoonTimes.add(afternoon.toInt())
                    notes.add(note)

                    Toast.makeText(this, "Entry saved!", Toast.LENGTH_SHORT).show()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Enter valid numbers for screen time.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Clear Button logic
        btnClear.setOnClickListener {
            etDate.text.clear()
            etMorning.text.clear()
            etAfternoon.text.clear()
            etNotes.text.clear()
        }

        // Details Button logic
        btnDetails.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putStringArrayListExtra("dates", ArrayList(dates))
            intent.putIntegerArrayListExtra("morningTimes", ArrayList(morningTimes))
            intent.putIntegerArrayListExtra("afternoonTimes", ArrayList(afternoonTimes))
            intent.putStringArrayListExtra("notes", ArrayList(notes))
            startActivity(intent)
        }
    }
}
