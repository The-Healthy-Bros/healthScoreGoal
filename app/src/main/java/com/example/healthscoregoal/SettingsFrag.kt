package com.example.healthscoregoal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SettingsFrag : Fragment() {
    lateinit var delButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        delButton = view.findViewById(R.id.buttonDeleteSleep)

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