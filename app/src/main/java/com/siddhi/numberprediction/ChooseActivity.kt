package com.siddhi.numberprediction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.siddhi.numberprediction.EasyActivity
import com.siddhi.numberprediction.HardActivity
import com.siddhi.numberprediction.NormalActivity

class ChooseActivity : AppCompatActivity() {

    lateinit var btnEasy: Button
    lateinit var btnNormal: Button
    lateinit var btnHard:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_level)

        btnEasy=findViewById(R.id.btnEasy)
        btnNormal=findViewById(R.id.btnNormal)
        btnHard=findViewById(R.id.btnHard)

        btnEasy.setOnClickListener {
            val intent= Intent(this@ChooseActivity,
                EasyActivity::class.java)
            startActivity(intent)
        }

        btnNormal.setOnClickListener {
            val intent= Intent(this@ChooseActivity,
                NormalActivity::class.java)
            startActivity(intent)
        }

        btnHard.setOnClickListener {
            val intent= Intent(this@ChooseActivity,
                HardActivity::class.java)
            startActivity(intent)
        }
    }

}
