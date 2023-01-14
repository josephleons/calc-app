package com.example.agelnminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    var lastNumeric :Boolean =false
    var lastDot:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        }

    fun onDigit(view:View){
        val tvInput:TextView = findViewById(R.id.tvInput)
        tvInput.append((view as Button).text)
        lastNumeric =true
    }

    fun onClear(view:View){
        var tvInput:TextView = findViewById(R.id.tvInput)
        tvInput.text =""
        lastNumeric =false
        lastDot= false
    }


    fun onDecimalPoint(view:View){
        if (lastNumeric && !lastDot){
            var tvInput:TextView =findViewById(R.id.tvInput)
            tvInput.append(".")
            lastNumeric =false
            lastDot =true
        }

    }
    fun onEqual(view:View){
        val tvInput:TextView =findViewById(R.id.tvInput)
        if(lastNumeric){
            var tvValue = tvInput.text.toString()

            try {
                if(tvValue.contains("-")){
                    val splitValue =tvValue.split("-")
                    var one =splitValue[0]
                    var two =splitValue[1]

                    tvInput.text =removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())                }

                else if(tvValue.contains("*")){
                    val splitValue =tvValue.split("*")
                    var one =splitValue[0]
                    var two =splitValue[1]
                    tvInput.text =removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvValue.contains("/")){
                    val splitValue =tvValue.split("/")
                    var one =splitValue[0]
                    var two =splitValue[1]
                    tvInput.text =removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

                else if(tvValue.contains("+")){
                    val splitValue =tvValue.split("+")
                    var one =splitValue[0]
                    var two =splitValue[1]
                    tvInput.text =removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    fun removeZeroAfterDot(s: String): String {
        if (s.contains(".0")) {
            val i = s.indexOf(".0")
           return s.substring(0 ,s.length-2)
        }
        return s
    }

    fun onOperator(view: View){
        var tvInput:TextView =findViewById(R.id.tvInput)
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric =false
            lastDot =false
        }
    }

    private fun isOperatorAdded(value: String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*")
                    ||value.contains("+")||value.contains("-")
        }
    }

    }