package com.example.healthscoregoal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

const val EXTRA_ENTRY = "EXTRA_ENTRY"

class DetailActivity : AppCompatActivity() {


    lateinit var dayInput: EditText
    lateinit var sleepInput: EditText
    lateinit var sleepBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dayInput = findViewById(R.id.eTDay)
        sleepInput = findViewById(R.id.eTHoursSlept)
        sleepBtn = findViewById(R.id.sleepButton)

        sleepBtn.setOnClickListener {
            if(dayInput.text.isNotEmpty() && sleepInput.text.isNotEmpty()){
                val intent = Intent(this, MainActivity::class.java)
                Log.d("DTAILACT", dayInput.text.toString());
                val s = sleepInput.text.toString()
                val day = Fitness(0,dayInput.text.toString(), s.toInt())
                intent.putExtra(EXTRA_ENTRY, day)
                this.startActivity(intent)
            }
        }
    }
}