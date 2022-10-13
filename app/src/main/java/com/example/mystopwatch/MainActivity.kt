package com.example.mystopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mystopwatch.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var time = 0
    var timerTask: Timer?=null
    var isRunning = false
    var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener{
            isRunning =! isRunning

            if(isRunning){
                watchStart()
            }else{
                watchPause()
            }
        }
        binding.labButton.setOnClickListener {
            recordLap()
        }
        binding.resetFab.setOnClickListener {
            watchReset()
        }
    }

    fun watchStart() {
        binding.fab.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24)

        timerTask = timer(period = 10) {
            time++
            val sec = time / 100
            val milli = time % 100


            runOnUiThread{
                binding.secView.text = "$sec"
                binding.milliSecView.text = "$milli"
            }


        }
    }

    fun watchPause() {
        binding.fab.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
        timerTask?.cancel()
    }

    fun recordLap() {
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAB : ${lapTime / 100}.${lapTime % 100}"

        binding.labLayout.addView(textView, 0)
        lap++
    }

    fun watchReset() {
        timerTask?.cancel()

        time = 0
        isRunning = false
        binding.fab.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
        binding.secView.text = "0"
        binding.milliSecView.text = "00"

        binding.labLayout.removeAllViews()
        lap = 1
    }

}