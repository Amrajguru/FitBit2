package com.example.fitbit2


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InputActivity : AppCompatActivity() {
    private lateinit var sleepTypeTextView: EditText
    private lateinit var sleepHrsTextView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_item)

        sleepTypeTextView = findViewById(R.id.SleepAmountInput)
        sleepHrsTextView = findViewById(R.id.SleepTypeInput)


        var itembutton = findViewById<Button>(R.id.itembutton)

        itembutton.setOnClickListener {
            var sleeptypetext: String = sleepTypeTextView.text.toString()
            var sleephrsnum: String = sleepHrsTextView.text.toString()
            lifecycleScope.launch(Dispatchers.IO) {
                (application as InputApplication).db.inputDao().insert(
                    InputEntity(
                        sleepType = sleeptypetext,
                        sleepHours = sleephrsnum
                    )
                )
            }
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }
}