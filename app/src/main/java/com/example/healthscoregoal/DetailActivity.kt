package com.example.healthscoregoal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

const val EXTRA_ENTRY = "EXTRA_ENTRY"
const val FOOD_ENTRY = "FOOD_ENTRY"

class DetailActivity : AppCompatActivity() {


    lateinit var dayInput: EditText
    lateinit var sleepInput: EditText
    lateinit var foodName: EditText
    lateinit var sugName:  EditText
    lateinit var carbName: EditText
    lateinit var calName:  EditText
    lateinit var sleepBtn: Button
    lateinit var foodBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dayInput = findViewById(R.id.eTDay)
        sleepInput = findViewById(R.id.eTHoursSlept)
        foodName = findViewById(R.id.eTFoodName)
        sugName = findViewById(R.id.eTSugar)
        carbName = findViewById(R.id.eTCarb)
        calName = findViewById(R.id.eTCalory)
        sleepBtn = findViewById(R.id.sleepButton)
        foodBtn = findViewById(R.id.foodButton1)

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
    }
}