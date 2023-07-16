package com.vivek.temperatureconverter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val c = "Celsius"
        const val f = "Fahrenheit"
        const val k = "Kelvin"
    }

    private var temp1 = c
    private var temp2 = f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        btTempClick()
    }

    private fun initView() {
        btTemp1.text = temp1
        btTemp2.text = temp2
        button.setOnClickListener {
            if (etTemp1.text!!.isNotEmpty())
                countTemp()
            else {
                etTemp1.error = "Blank"
                Toast.makeText(this, "Form must be filled in", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun btTempClick() {
        btTemp1.setOnClickListener {
            buildDialog(1)
        }

        btTemp2.setOnClickListener {
            buildDialog(2)
        }
    }

    private fun buildDialog(tempNum : Int) {
        val singleItems = arrayOf(c, f, k)

        MaterialAlertDialogBuilder(this@MainActivity)
            .setTitle("Select One")
            .setItems(singleItems) { dialog, which ->
                if (tempNum == 1) {
                    temp1 = singleItems[which]
                    btTemp1.text = temp1
                } else {
                    temp2 = singleItems[which]
                    btTemp2.text = temp2
                }
            }
            .show()
    }

    private fun countTemp() {
        when {
            temp1 == c && temp2 == f -> {
                val value = ctof(etTemp1.text.toString().toDouble()).toString()
                tvTempValue.text = value
            }

            temp1 == c && temp2 == k -> {
                val value = ctok(etTemp1.text.toString().toDouble()).toString()
                tvTempValue.text = value
            }
            temp1 == f && temp2 == c -> {
                val value = ftoc(etTemp1.text.toString().toDouble()).toString()
                tvTempValue.text = value
            }

            temp1 == f && temp2 == k -> {
                val value = ftok(etTemp1.text.toString().toDouble()).toString()
                tvTempValue.text = value
            }

            temp1 == k && temp2 == c -> {
                val value = ktoc(etTemp1.text.toString().toDouble()).toString()
                tvTempValue.text = value
            }
            temp1 == k && temp2 == f -> {
                val value = ktof(etTemp1.text.toString().toDouble()).toString()
                tvTempValue.text = value
            }

            else -> Toast.makeText(this, "Cannot convert 2 same temperature", Toast.LENGTH_LONG).show()
        }
    }

    private fun ctof(c: Double): Double {
        return c * (9.0 / 5.0) + 32.0
    }
    private fun ctok(c: Double): Double {
        return c + 273.15
    }
    private fun ftoc(f: Double): Double {
        return (f - 32.0) * (5.0 / 9.0)
    }
    private fun ftok(f: Double): Double {
        return ((f - 32.0) * (5.0 / 9.0)) - 273.15
    }
    private fun ktoc(k: Double): Double {
        return k - 273.15
    }
    private fun ktof(k: Double): Double {
        return (k - 273.15) * (9.0 / 5.0) + 32.0
    }
}