package com.siddhi.numberprediction

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.siddhi.numberprediction.R

class MyRecordsActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    //Easy
    lateinit var txtPrintNameEasy:TextView
    lateinit var txtPrintAttemptsEasy:TextView
    var nameEasy:String?="None"
    var attemptsEasy:String?="0"

    //Normal
    lateinit var txtPrintNameNormal:TextView
    lateinit var txtPrintAttemptsNormal:TextView
    var nameNormal:String?="None"
    var attemptsNormal:String?="0"

    //Hard
    lateinit var txtPrintNameHard:TextView
    lateinit var txtPrintAttemptsHard:TextView
    var nameHard:String?="None"
    var attemptsHard:String?="0"
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences=getSharedPreferences(getString(R.string.preferences_file_name),Context.MODE_PRIVATE)
        setContentView(R.layout.activity_my_records)

        //Easy
        txtPrintNameEasy=findViewById(R.id.txtPrintNameEasy)
        txtPrintAttemptsEasy=findViewById(R.id.txtPrintAttemptsEasy)

        nameEasy=sharedPreferences.getString("nameEasy","None")
        attemptsEasy=sharedPreferences.getString("attemptsEasy","0")
        txtPrintNameEasy.text=nameEasy
        txtPrintAttemptsEasy.text=attemptsEasy
        sharedPreferences.edit().putString("newHighEasy",attemptsEasy).apply()


        //Normal
        txtPrintNameNormal=findViewById(R.id.txtPrintNameNormal)
        txtPrintAttemptsNormal=findViewById(R.id.txtPrintAttemptsNormal)

        nameNormal=sharedPreferences.getString("nameNormal","None")
        attemptsNormal=sharedPreferences.getString("attemptsNormal","0")
        txtPrintNameNormal.text=nameNormal
        txtPrintAttemptsNormal.text=attemptsNormal
        sharedPreferences.edit().putString("newHighNormal",attemptsNormal).apply()

        //Hard
        txtPrintNameHard=findViewById(R.id.txtPrintNameHard)
        txtPrintAttemptsHard=findViewById(R.id.txtPrintAttemptsHard)

        nameHard=sharedPreferences.getString("nameHard","None")
        attemptsHard=sharedPreferences.getString("attemptsHard","0")
        txtPrintNameHard.text=nameHard
        txtPrintAttemptsHard.text=attemptsHard
        sharedPreferences.edit().putString("newHighHard",attemptsHard).apply()

    }


}
    