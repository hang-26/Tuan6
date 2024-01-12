package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var showAdd = false
    var showSub = false
    var showMul = false
    var showRandom = false
    private var valueIncrease = 0
    private var valueDecrease = 0
    private var valueMultiply = 1
    private var valueRandom = 1
    private var jobIncrease: Job? = null
    private var jobDecrease: Job? = null
    private var jobMultiply: Job? = null
    private var jobRandom: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handlerEvent()
    }

    fun handlerEvent() {
        binding.btAdd.setOnClickListener {
            startIncrease()
        }

        binding.btSub.setOnClickListener {
            startDecrease()
        }
        binding.btMul.setOnClickListener {
            startMultiplying()
        }
        binding.btRandom.setOnClickListener {
            startRandom()
        }
    }

    fun startIncrease() {
        showAdd = !showAdd
        jobIncrease?.cancel()

        jobIncrease = CoroutineScope(Dispatchers.Main). launch {
            if (showAdd == true) {
                while (showAdd) {
                    delay(1000)
                    valueIncrease++
                    updateTextView(binding.tvResultAdd, valueIncrease)
                }
            }

        }
    }

     fun startMultiplying() {
        showMul = !showMul
        jobMultiply?.cancel()

        jobMultiply = CoroutineScope(Dispatchers.Main).launch {
            if (showMul == true) {
                while (showMul) {
                    delay(1000)
                    valueMultiply *= 2 // Nhân giá trị lên
                    updateTextView(binding.tvResultMul, valueMultiply)
                }
            }

        }
    }

   fun startDecrease() {
       showSub = !showSub
       jobDecrease?.cancel()

       jobDecrease = CoroutineScope(Dispatchers.Main).launch {
           if (showSub == true) {
               while (showSub) {
                   delay(1000)
                   valueDecrease --
                   updateTextView(binding.tvResultSub, valueDecrease)
               }
           }
       }
   }

    fun startRandom() {
        showRandom = !showRandom
        jobDecrease?.cancel()

        jobDecrease = CoroutineScope(Dispatchers.Main).launch {
            if (showRandom == true) {
                while (showRandom) {
                    delay(1000)
                    valueRandom = Random.nextInt(500)
                    binding.tvResultRandom.text = valueRandom.toString()
                    updateTextView(binding.tvResultSub, valueDecrease)
                }
            }
        }
    }

    private fun updateTextView(textView: TextView, value: Int) {
        runOnUiThread {
            textView.text = value.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        jobIncrease?.cancel()
        jobDecrease?.cancel()
        jobMultiply?.cancel()
        jobRandom?.cancel()
    }
//

}