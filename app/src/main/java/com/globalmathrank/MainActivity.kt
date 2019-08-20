package com.globalmathrank

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle Addition Operation
        btnStartAddition.setOnClickListener() {
            val intentAddition = Intent(this,AdditionMain::class.java)
            intentAddition.putExtra("EstTime", 20)
            intentAddition.putExtra("NumTest", 16)
            startActivity(intentAddition)
        }

        // Handle Negation Operation
        btnStartNegation.setOnClickListener() {
        }

        // Handle Multiplication Operation
        btnStartMultiplication.setOnClickListener() {
        }

        // Handle Division Operation
        btnStartDivision.setOnClickListener() {
        }

        // Handle Exit Button
        btnReturn.setOnClickListener() {
            val alertExit = AlertDialog.Builder(this)
            alertExit.setTitle("Global MathRank")
            alertExit.setMessage("Are You Really Want To EXIT this App?")
            alertExit.setCancelable(false)
            alertExit.setPositiveButton("YES") {_, _->
                Toast.makeText(this, "App Will Exit Now!", Toast.LENGTH_LONG).show()
                finishAffinity()
                System.exit(0)
            }
            alertExit.setNegativeButton("NO") {_, _->
                Toast.makeText(this, "Exit App Cancelled!", Toast.LENGTH_LONG).show()
            }
            alertExit.setNeutralButton("CANCEL") {_,_->
                Toast.makeText(this, "Exit App Cancelled!", Toast.LENGTH_LONG).show()
            }
            alertExit.show()
        }
    }
}
