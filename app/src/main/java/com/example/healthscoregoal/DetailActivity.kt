package com.example.healthscoregoal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.serialization.SerialName

const val EXTRA_ENTRY = "EXTRA_ENTRY"
const val FOOD_ENTRY = "FOOD_ENTRY"
const val EXERCISE_ENTRY = "EXERCISE_ENTRY"

class DetailActivity : AppCompatActivity() {


    lateinit var dayInput: EditText
    lateinit var sleepInput: EditText
    lateinit var foodName: EditText
    lateinit var sugName:  EditText
    lateinit var carbName: EditText
    lateinit var calName:  EditText
    lateinit var exDay: EditText
    lateinit var exName: EditText
    lateinit var exLength: EditText
    lateinit var calsBurnt: EditText
    lateinit var sleepBtn: Button
    lateinit var foodBtn: Button
    lateinit var exBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dayInput = findViewById(R.id.eTDay)
        sleepInput = findViewById(R.id.eTHoursSlept)
        foodName = findViewById(R.id.eTFoodName)
        sugName = findViewById(R.id.eTSugar)
        carbName = findViewById(R.id.eTCarb)
        calName = findViewById(R.id.eTCalory)
        exDay = findViewById(R.id.eTexDay)
        exName = findViewById(R.id.eTexWName)
        exLength = findViewById(R.id.eTexWLength)
        calsBurnt = findViewById(R.id.eTexRepsMiles)
        sleepBtn = findViewById(R.id.sleepButton)
        foodBtn = findViewById(R.id.foodButton1)
        exBtn = findViewById(R.id.exButton)

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
        foodBtn.setOnClickListener{
            if(foodName.text.isNotEmpty()){
                val intent = Intent(this, MainActivity::class.java)
                val su = sugName.text.toString()
                val cb = carbName.text.toString()
                val cl = calName.text.toString()
                val food = Foods(0, foodName.text.toString(), su.toInt(), cb.toInt(), cl.toInt())
                intent.putExtra(FOOD_ENTRY, food)
                this.startActivity(intent)
            }
        }
        exBtn.setOnClickListener{
            if(exDay.text.isNotEmpty() && exName.text.isNotEmpty() && exLength.text.isNotEmpty() && calsBurnt.text.isNotEmpty()){
                val intent = Intent(this, MainActivity::class.java)
                val exCal = calsBurnt.text.toString()
                val ex = Exercises(0,exDay.text.toString(), exName.text.toString(), exLength.text.toString(), exCal.toInt())
                intent.putExtra(EXERCISE_ENTRY, ex)
                this.startActivity(intent)
            }
        }
    }
}