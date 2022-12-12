package com.example.healthscoregoal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.healthscoregoal.MainActivity.Companion.minS
import com.example.healthscoregoal.MainActivity.Companion.maxS
import com.example.healthscoregoal.MainActivity.Companion.minCarb
import com.example.healthscoregoal.MainActivity.Companion.maxCarb
import com.example.healthscoregoal.MainActivity.Companion.minCal
import com.example.healthscoregoal.MainActivity.Companion.maxCal


const val CARB_ENTRY = "CARB_ENTRY"
const val SUGAR_ENTRY = "SUGAR_ENTRY"
const val CAL_ENTRY = "CAL_ENTRY"
class SettingsFrag : Fragment() {
    lateinit var delButton: Button
    lateinit var minSugar: EditText
    lateinit var maxSugar: EditText
    lateinit var minCarbs: EditText
    lateinit var maxCarbs: EditText
    lateinit var minCals: EditText
    lateinit var maxCals: EditText
    lateinit var apiUButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        minSugar = view.findViewById(R.id.etMinSugar)
        maxSugar = view.findViewById(R.id.etMaxSugar)
        minCarbs = view.findViewById(R.id.etMinCarbs)
        maxCarbs = view.findViewById(R.id.etMaxCarbs)
        minCals = view.findViewById(R.id.etminCals)
        maxCals = view.findViewById(R.id.etMaxCals)
        apiUButton = view.findViewById(R.id.updateApiButton)
        delButton = view.findViewById(R.id.buttonDeleteSleep)

        apiUButton.setOnClickListener {
            if(minSugar.text.isNotEmpty() && maxSugar.text.isNotEmpty()){
                minS = minSugar.text.toString().toInt()
                maxS = maxSugar.text.toString().toInt()
            }
            if (minCarbs.text.isNotEmpty() && maxCarbs.text.isNotEmpty()) {
                minCarb = minCarbs.text.toString().toInt()
                maxCarb = maxCarbs.text.toString().toInt()
            }
            if(minCals.text.isNotEmpty() && maxCals.text.isNotEmpty()){
                minCal = minCals.text.toString().toInt()
                maxCal = maxCals.text.toString().toInt()
            }
        }

        delButton.setOnClickListener {
            val intent = Intent(it.context, DeleteActivity::class.java)
            this.startActivity(intent)
        }
        return view
    }

    companion object {
        fun newInstance(): SettingsFrag {
            return  SettingsFrag()
        }
    }
}