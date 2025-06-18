package com.example.screentime

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {

    private lateinit var summaryText: TextView
    private lateinit var entryLayout: LinearLayout
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        summaryText = findViewById(R.id.tvSummary)
        entryLayout = findViewById(R.id.entryContainer)
        btnBack = findViewById(R.id.btnBack)

        val dates = intent.getStringArrayListExtra("dates")!!
        val morningTimes = intent.getIntegerArrayListExtra("morningTimes")!!
        val afternoonTimes = intent.getIntegerArrayListExtra("afternoonTimes")!!
        val notes = intent.getStringArrayListExtra("notes")!!

        var total = 0
        for (i in dates.indices) {
            val entry = TextView(this)
            val totalTime = morningTimes[i] + afternoonTimes[i]
            total += totalTime
            entry.text = "${dates[i]}: $totalTime mins (${notes[i]})"
            entry.setPadding(8, 8, 8, 8)
            entryLayout.addView(entry)
        }

        val average = if (dates.isNotEmpty()) total / dates.size else 0
        summaryText.text = "Average Screen Time: $average mins/day"

        btnBack.setOnClickListener {
            finish()
        }
    }
}
