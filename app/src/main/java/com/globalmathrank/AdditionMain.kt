package com.globalmathrank

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_addition_main.*

class AdditionMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition_main)

        // Handle Basic Addition Operation
        btnAdditionBasic.setOnClickListener() {
            val intentAddition = Intent(this,AdditionBasic::class.java)
            intentAddition.putExtra("EstTime", 16)
            intentAddition.putExtra("NumTest", 16)
            startActivity(intentAddition)
        }

        // Handle Addition MixPlace Operation
        btnAdditionMixPlace.setOnClickListener() {
            val intentAddition = Intent(this,AdditionMixPlace::class.java)
            intentAddition.putExtra("EstTime", 24)
            intentAddition.putExtra("NumTest", 16)
            startActivity(intentAddition)
        }

        // Handle Addition Mix Color Operation
        btnAdditionMixColor.setOnClickListener() {
            val intentAddition = Intent(this,AdditionMixColored::class.java)
            intentAddition.putExtra("EstTime", 32)
            intentAddition.putExtra("NumTest", 16)
            startActivity(intentAddition)
        }

        // Handle Addition Mix Picture Operation
        btnAdditionMixPicture.setOnClickListener() {
            val intentAddition = Intent(this,AdditionMixPicture::class.java)
            intentAddition.putExtra("EstTime", 20)
            intentAddition.putExtra("NumTest", 16)
            startActivity(intentAddition)
        }

        // Return Control Button Listener
        btnReturn.setOnClickListener() {

            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }

    }
}
