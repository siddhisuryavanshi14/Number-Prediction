package com.siddhi.numberprediction

import android.content.Intent
import android.media.MediaPlayer
import android.media.audiofx.BassBoost
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.siddhi.numberprediction.MyRecordsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var btnPlayButton: Button
    lateinit var btnMyRecords:Button
    lateinit var btnHelp:Button
    lateinit var ibInfo: ImageButton
    lateinit var ibVolume:ImageButton
    lateinit var mediaPlayer: MediaPlayer
    var i=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlayButton=findViewById(R.id.btnPlayGame)
        btnMyRecords=findViewById(R.id.btnMyRecords)
        btnHelp=findViewById(R.id.btnHelp)
        ibInfo=findViewById(R.id.ibInfo)
        ibVolume=findViewById(R.id.ibVolume)
        val mediaPlayer=MediaPlayer.create(this, R.raw.sound)
        mediaPlayer.setLooping(true)
        mediaPlayer.setVolume(100F, 100F)
        if (i==1) {
            mediaPlayer.start()
        }

        btnPlayButton.setOnClickListener {
            val intent= Intent(this@MainActivity,
                ChooseActivity::class.java)
            startActivity(intent)
        }

        btnMyRecords.setOnClickListener {
            val intent= Intent(this@MainActivity,
                MyRecordsActivity::class.java)
            startActivity(intent)
        }

        btnHelp.setOnClickListener {
            val intent= Intent(this@MainActivity,
                HelpActivity::class.java)
            startActivity(intent)
        }

        ibInfo.setOnClickListener {
            val intent= Intent(this@MainActivity,
                InfoActivity::class.java)
            startActivity(intent)
        }

        ibVolume.setOnClickListener {
            if (i==1) {
                i = 0
                ibVolume.setImageResource(R.drawable.ic_volume_off)
                mediaPlayer.pause()
                Toast.makeText(applicationContext,"Volume Off", Toast.LENGTH_SHORT).show()
            } else {
                i = 1
                ibVolume.setImageResource(R.drawable.ic_volume_up)
                mediaPlayer.start()
                Toast.makeText(applicationContext,"Volume Up", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}
