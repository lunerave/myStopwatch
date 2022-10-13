package com.example.mystopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mystopwatch.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var time = 0
    var timerTask: Timer?=null
    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener{
            isRunning =! isRunning

            if(isRunning){
                start()
            }else{
                pause()
            }
        }
    }

    fun start() {
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

    fun pause() {
        binding.fab.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
        timerTask?.cancel()
    }


}