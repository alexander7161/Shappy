package com.example.victor.shappy

import android.content.ClipData.newIntent
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my_button.setOnClickListener {
            val intent = Intent(this, result::class.java)
            startActivity(intent)
        }
    }
}
