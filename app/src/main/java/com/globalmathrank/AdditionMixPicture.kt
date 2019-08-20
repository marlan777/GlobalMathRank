package com.globalmathrank

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_addition_basic.*
import kotlin.random.Random
import android.graphics.Color.BLUE as ColorBLUE

class AdditionMixPicture : AppCompatActivity() {

    // Init Questions
    var vNum1 : Int = 0     // Opr1
    var vNum2 : Int = 0     // Opr2
    var vPrevNum1 : Int = -1// Previous Opr1 Num
    var vPrevNum2 : Int = -1// Previous Opr1 Num
    var vTotalR: Int = 0    // Total vNum1+vNum2
    var vTotalQ: Int= 0     // Total (vNum1+vNum2)\10

    // Init Counters
    var vRightCounter : Int = 0     // Right Answers Counter
    var vWrongCounter : Int = 0     // Wrong Answers Counter
    var vTotalCounter : Int = 0     // Total Counter

    //var workTimer : CountDownTimer

    // Track Highest Time
    var vLowestTime : Long = 999999L// Lowest Time, in miliSeconds

    // Target Time to Complete
    var vTargetTime : Int = 0

    // Number of RightAnswer Needed
    var vTargetOK : Int = 0

    // Time Counter variable
    var tStart : Long = 0L
    var tStop : Long = 0L
    var tDuration : Long = 0L

    //var tStart2 : Long = 0L
    //var tStop2 : Long = 0L
    //var tDuration2 : Long = 0L

    // Function : Random Number Creator
    fun Random10():Int {
        return Random.nextInt(0,10)
    }

    // Function : Create Number Pair for question, and display it
    fun CreateQuestion(){
        // Create Opr1/Opr2, avoid same consecutive Opr1/Opr2
        do {
            vNum1 = Random10()
            vNum2 = Random10() }
        while ( (vNum1==vPrevNum1) && (vNum2==vPrevNum2))

        vTotalR = vNum1+vNum2

        txtOpr1.text = vNum1.toString()
        txtOpr2.text = vNum2.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition_basic)

        // Get Test Parameter from Intent
        vTargetTime = intent.extras.getInt("EstTime")
        vTargetOK = intent.extras.getInt("NumTest")

        // Init Displays
        txtvBestTime.text = vLowestTime.toString()
        txtvTargetTime.text = vTargetTime.toString()
        txtvTargetOK.text = vTargetOK.toString()

        // Start Control Button Listener
        btnStart.setOnClickListener() {
            // Set button position
            btnStart.setEnabled(false)
            btnStop.setEnabled(true)
            btnReturn.setEnabled(false)
            btnNum1.setEnabled(true)
            btnNum2.setEnabled(true)
            btnNum3.setEnabled(true)
            btnNum4.setEnabled(true)
            btnNum5.setEnabled(true)
            btnNum6.setEnabled(true)
            btnNum7.setEnabled(true)
            btnNum8.setEnabled(true)
            btnNum9.setEnabled(true)
            btnNum0.setEnabled(true)

            // Init Displays
            txtCountRight.text = "000"
            txtCountWrong.text = "000"
            txtCountTotal.text = "000"
            txtTimer.text = "000000"
            txtvBestTime.text = vLowestTime.toString()

            // Init Counters
            vRightCounter = 0     // Right Answers Counter
            vWrongCounter = 0     // Wrong Answers Counter
            vTotalCounter = 0     // Total Counter

            // Start Timer
            view_timer.isCountDown = false
            view_timer.setBase(SystemClock.elapsedRealtime())
            view_timer.start()

            // Get Exact Start Time
            //view_timer.setBase(System.currentTimeMillis())
            tStart = System.currentTimeMillis()
            //or
            //tStart2 = SystemClock.elapsedRealtime()

            // Set Normal Backroud Color for Answer
            txtAnswer.setBackgroundColor(Color.WHITE)

            // Create First Question
            CreateQuestion()

            //workTimer = object : CountDownTimer(3000, 1000) { // starts at 3 seconds
            //    override fun onTick(secondsUntilDone: Long) {
            //        Log.i("1 Second", "Still counting down")
            //    }

            //    override fun onFinish() {
            //        Log.i("We're done!", "No more countdown")
            //    }
            //}.start()
        }

        // Stop Control Button Listener
        btnStop.setOnClickListener() {
            // Stop Timer
            view_timer.stop()

            // Get Exact Stop Time
            tStop = System.currentTimeMillis()
            //tStop2 = SystemClock.elapsedRealtime()

            // Get Running Duration, in Millisecond
            tDuration = tStop - tStart
            //tDuration2 = tStop2 - tStart2
            txtTimer.text = tDuration.toString()
            //Log.i("TIMER2", tDuration2.toString())

            // Set button position
            btnStart.setEnabled(true)
            btnStop.setEnabled(false)
            btnReturn.setEnabled(true)
            btnNum1.setEnabled(false)
            btnNum2.setEnabled(false)
            btnNum3.setEnabled(false)
            btnNum4.setEnabled(false)
            btnNum5.setEnabled(false)
            btnNum6.setEnabled(false)
            btnNum7.setEnabled(false)
            btnNum8.setEnabled(false)
            btnNum9.setEnabled(false)
            btnNum0.setEnabled(false)
        }

        // Return Control Button Listener
        btnReturn.setOnClickListener {
            // Set button position
            btnStart.setEnabled(true)
            btnStop.setEnabled(false)
            btnReturn.setEnabled(true)

            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }

    // NumPad Control Button Listener
    fun numPadClickHandler(view : View){
        when (view.getId()) {
            R.id.btnNum1 -> vTotalQ = 1
            R.id.btnNum2 -> vTotalQ = 2
            R.id.btnNum3 -> vTotalQ = 3
            R.id.btnNum4 -> vTotalQ = 4
            R.id.btnNum5 -> vTotalQ = 5
            R.id.btnNum6 -> vTotalQ = 6
            R.id.btnNum7 -> vTotalQ = 7
            R.id.btnNum8 -> vTotalQ = 8
            R.id.btnNum9 -> vTotalQ = 9
            R.id.btnNum0 -> vTotalQ = 0
            else         -> vTotalQ = 0
        }

        // Check Answer and Update Counters
        if (vTotalR.rem(10)==vTotalQ) {
            txtAnswer.text = "OK"
            txtAnswer.setBackgroundColor(Color.WHITE)
            txtAnswer.setBackgroundColor(Color.BLUE)
            vRightCounter++
            txtCountRight.text = vRightCounter.toString().padStart(3,'0')
        }
        else {
            txtAnswer.text = "NOK"
            txtAnswer.setBackgroundColor(Color.WHITE)
            txtAnswer.setBackgroundColor(Color.RED)
            vWrongCounter++
            txtCountWrong.text = vWrongCounter.toString().padStart(3,'0')
        }
        vTotalCounter++
        txtCountTotal.text = vTotalCounter.toString().padStart(3,'0')

        if (vTotalCounter < vTargetOK) {
            // Create NextQuestion
            CreateQuestion()}
        else {
            // Stop Timer
            view_timer.stop()

            // Get Exact Stop Time
            tStop = System.currentTimeMillis()
            //tStop2 = SystemClock.elapsedRealtime()

            // Get Running Duration, in Millisecond
            tDuration = tStop - tStart
            //tDuration2 = tStop2 - tStart2
            txtTimer.text = tDuration.toString()
            //Log.i("TIMER2", tDuration2.toString())

            // Set button position
            btnStart.setEnabled(true)
            btnStop.setEnabled(false)
            btnReturn.setEnabled(true)
            btnNum1.setEnabled(false)
            btnNum2.setEnabled(false)
            btnNum3.setEnabled(false)
            btnNum4.setEnabled(false)
            btnNum5.setEnabled(false)
            btnNum6.setEnabled(false)
            btnNum7.setEnabled(false)
            btnNum8.setEnabled(false)
            btnNum9.setEnabled(false)
            btnNum0.setEnabled(false)

            if (vWrongCounter==0) {
                Toast.makeText(this, "SUCCESS, in "+tDuration.toString()+" miliSeconds", Toast.LENGTH_LONG).show()
                if (tDuration<vLowestTime) {
                    vLowestTime = tDuration

                    txtvBestTime.text = vLowestTime.toString()
                }
            }
            else {
                Toast.makeText(this, "FAILED. " + vWrongCounter.toString() + " WRONG, in "+ vRightCounter.toString()+ tDuration.toString()+" miliSeconds", Toast.LENGTH_LONG).show()
            }

            // Reset Questions & Answer Display
            txtOpr1.text = "X"
            txtOpr2.text = "Y"
            txtAnswer.setBackgroundColor(Color.WHITE)
        }
    }
}
