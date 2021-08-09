package com.siddhi.numberprediction

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.icu.text.DateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_dialog.view.*
import kotlinx.android.synthetic.main.activity_entername.view.*

class HardActivity : AppCompatActivity() {

    lateinit var btnStart: Button
    lateinit var etInput: String
    lateinit var txtPrediction: TextView
    lateinit var etInputName:String
    lateinit var txtDigit:TextView
    lateinit var sharedPreferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences=getSharedPreferences(getString(R.string.preferences_file_name),Context.MODE_PRIVATE)
        setContentView(R.layout.activity_descriptive)

        btnStart=findViewById(R.id.btnStart)
        txtPrediction=findViewById(R.id.txtPrediction)
        txtDigit=findViewById(R.id.txtDigit)
        txtDigit.text="(4 Digit Number)"

        btnStart.setOnClickListener {
            /*Handler().postDelayed({
                progressBar.visibility= View.VISIBLE
            },1000)*/
            txtPrediction.text = null
            var count = 1
            var availCount = 15
            val number = (1000..9999).random()

            val mDialogView = layoutInflater.inflate(R.layout.activity_dialog, null)

            val dialog = AlertDialog.Builder(this)
            dialog.setView(mDialogView)
            dialog.setNegativeButton("Exit") { text, listener ->
                //Do Nothing
            }
            dialog.setCancelable(false)
            val alert = dialog.create()
            alert.show()
            mDialogView.txtCount.setText("Attempts = $availCount")
            mDialogView.btnCheck.setOnClickListener { v ->
                //To close keyboard after clicking Check button
                if (mDialogView.etInput.text.toString().trim().isNotEmpty()) {
                    etInput = mDialogView.etInput.text.toString()
                    if (1000 <= etInput.toInt() && etInput.toInt() < 10000)
                        availCount -= 1
                    if (availCount > 0) {
                        mDialogView.txtCount.setText("Attempts = $availCount")
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                        imm?.hideSoftInputFromWindow(v.windowToken, 0)
                        //to save user input
                        if (1000 <= etInput.toInt() && etInput.toInt() < 10000) {
                            if (number > etInput.toInt()) {
                                //checking number is lower
                                Toast.makeText(
                                    this,
                                    "$etInput is lower than the generated number!",
                                    Toast.LENGTH_LONG
                                ).show()
                                count += 1

                            } else if (number < etInput.toInt()) {
                                //checking umber is higher
                                Toast.makeText(
                                    this,
                                    "$etInput is higher than the generated number!",
                                    Toast.LENGTH_LONG
                                ).show()
                                count += 1
                            } else {
                                var prevHighScore: String? =
                                    sharedPreferences.getString("newHighHard", "0")
                                alert.dismiss()
                                val msgDialog = AlertDialog.Builder(this)
                                if (prevHighScore != null) {
                                    if (prevHighScore.toInt() >= count || prevHighScore == "0") {
                                        msgDialog.setMessage("Congratulations!! $etInput is the correct number. Its your Highscore")
                                    } else if (prevHighScore.toInt() <= count) {
                                        msgDialog.setMessage("Congratulations!! $etInput is the correct number.")
                                    }
                                }
                                val positiveButton =
                                    msgDialog.setPositiveButton("Ok") { text, listner ->
                                        //Do nothing


                                        if (prevHighScore != null) {
                                            if (prevHighScore.toInt() >= count || prevHighScore == "0") {
                                                val mNameView = layoutInflater.inflate(
                                                    R.layout.activity_entername,
                                                    null
                                                )
                                                val nameDialog = AlertDialog.Builder(this)
                                                nameDialog.setView(mNameView)
                                                nameDialog.setPositiveButton("Done") { text, listeners ->
                                                    etInputName =
                                                        mNameView.etInputName.text.toString()
                                                    savePreferences(etInputName, count.toString())
                                                    Toast.makeText(
                                                        this,
                                                        "Highscore saved successfully!",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                                nameDialog.setNegativeButton("Cancel") { text, listeners ->
                                                    //Do Nothing
                                                }
                                                nameDialog.create()
                                                nameDialog.show()
                                            }
                                        }
                                    }
                                msgDialog.create()
                                msgDialog.show()
                                txtPrediction.text =
                                    "You have Predicted the correct number in $count attempts!!!"
                            }
                        } else {
                            Toast.makeText(
                                this,
                                "Please enter a number between 1000 to 9999",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    } else {
                        Toast.makeText(
                            this,
                            "Attempts Finished! Game Over...",
                            Toast.LENGTH_LONG
                        ).show()
                        alert.dismiss()
                        txtPrediction.text = "Better Luck Next time!"
                    }

                } else
                    Toast.makeText(
                        this,
                        "Please enter a number",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    fun savePreferences(name: String,attempts:String) {
        sharedPreferences.edit().putString("nameHard",name).apply()
        sharedPreferences.edit().putString("attemptsHard",attempts).apply()
    }
}
