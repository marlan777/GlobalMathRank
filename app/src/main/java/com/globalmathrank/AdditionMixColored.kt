package com.globalmathrank

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_addition_basic.*
import kotlin.random.Random


class AdditionMixColored : AppCompatActivity() {

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


    // Function : Random Number Creator
    fun Random10():Int {
        return Random.nextInt(0,10)
    }

    // Extention : Shuffle Array
    fun <T> Array<T>.shuffle(): Array<T> {

        for (index in 0..this.size - 1) {
            val randomIndex = Random.nextInt(index)

            // Swap with the random position
            val temp = this[index]
            this[index] = this[randomIndex]
            this[randomIndex] = temp
        }
        return this
    }

    // Prepare Keyboard Content. Randomize Positions
    fun PrepareKey() {
        // Shuffle Keyboard Number
        var vOriginalKey = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,0)
        vOriginalKey.shuffle()

        // Shuffle Color Code
        var vOriginalColor = arrayListOf<Int>(Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY ,Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.WHITE, Color.YELLOW)
        vOriginalColor.shuffle()

        // Put Button Key
        btnNum1.text = vOriginalKey[1].toString()
        btnNum2.text = vOriginalKey[2].toString()
        btnNum3.text = vOriginalKey[3].toString()
        btnNum4.text = vOriginalKey[4].toString()
        btnNum5.text = vOriginalKey[5].toString()
        btnNum6.text = vOriginalKey[6].toString()
        btnNum7.text = vOriginalKey[7].toString()
        btnNum8.text = vOriginalKey[8].toString()
        btnNum9.text = vOriginalKey[9].toString()
        btnNum0.text = vOriginalKey[0].toString()

        // Put Button Color
        btnNum1.setBackgroundColor(vOriginalColor[1])
        btnNum2.setBackgroundColor(vOriginalColor[2])
        btnNum3.setBackgroundColor(vOriginalColor[3])
        btnNum4.setBackgroundColor(vOriginalColor[4])
        btnNum5.setBackgroundColor(vOriginalColor[5])
        btnNum6.setBackgroundColor(vOriginalColor[6])
        btnNum7.setBackgroundColor(vOriginalColor[7])
        btnNum8.setBackgroundColor(vOriginalColor[8])
        btnNum9.setBackgroundColor(vOriginalColor[9])
        btnNum0.setBackgroundColor(vOriginalColor[0])

        // Optional. Coloring the Opr1/Opr2
        //txtOpr1.setBackgroundColor(vOriginalColor[3])
        //txtOpr2.setBackgroundColor(vOriginalColor[7])
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

            // Set button positions
            PrepareKey()

            // Set button status
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
            tStart = System.currentTimeMillis()

            // Set Normal Backroud Color for Answer
            txtAnswer.setBackgroundColor(Color.WHITE)

            // Create First Question
            CreateQuestion()
        }

        // Stop Control Button Listener
        btnStop.setOnClickListener() {
            // Stop Timer
            view_timer.stop()

            // Get Exact Stop Time
            tStop = System.currentTimeMillis()

            // Get Running Duration, in Millisecond
            tDuration = tStop - tStart
            txtTimer.text = tDuration.toString()

            // Set button status
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

            // Reset Questions & Answer Display
            txtOpr1.text = "X"
            txtOpr2.text = "Y"
            txtAnswer.setBackgroundColor(Color.WHITE)
        }

        // Return Control Button Listener
        btnReturn.setOnClickListener {
            // Set button position
            btnStart.setEnabled(true)
            btnStop.setEnabled(false)
            btnReturn.setEnabled(true)

            val intentAdditionMain = Intent(this, AdditionMain::class.java)
            startActivity(intentAdditionMain)
        }
    }

    // NumPad Control Button Listener
    fun numPadClickHandler(view : View) {
        // Get Text-Button-Pressed Value
        vTotalQ = Integer.valueOf(findViewById<Button>(view.getId()).getText().toString())

        // Check Answer and Update Counters
        if (vTotalR.rem(10) == vTotalQ) {
            txtAnswer.text = "OK"
            txtAnswer.setBackgroundColor(Color.WHITE)
            txtAnswer.setBackgroundColor(Color.BLUE)
            vRightCounter++
            txtCountRight.text = vRightCounter.toString().padStart(3, '0')
        } else {
            txtAnswer.text = "NOK"
            txtAnswer.setBackgroundColor(Color.WHITE)
            txtAnswer.setBackgroundColor(Color.RED)
            vWrongCounter++
            txtCountWrong.text = vWrongCounter.toString().padStart(3, '0')
        }
        vTotalCounter++
        txtCountTotal.text = vTotalCounter.toString().padStart(3, '0')

        if (vTotalCounter < vTargetOK) {
            // Create NextQuestion
            CreateQuestion()

            // Set button positions
            PrepareKey()
        }
        else {
            // Stop Timer
            view_timer.stop()

            // Get Exact Stop Time
            tStop = System.currentTimeMillis()

            // Get Running Duration, in Millisecond
            tDuration = tStop - tStart
            txtTimer.text = tDuration.toString()

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
