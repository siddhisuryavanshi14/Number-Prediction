package com.siddhi.numberprediction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.siddhi.numberprediction.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        title=" "

        Handler().postDelayed({
            val startAct= Intent(this@SplashActivity,
                MainActivity::class.java)
            startActivity(startAct)
        },4000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
