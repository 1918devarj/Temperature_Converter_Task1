package com.example.temperature_converter

import android.widget.EditText
import android.widget.TextView
import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.content.DialogInterface
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private lateinit var selectType : LinearLayout
    private lateinit var textType : TextView
    private lateinit var editInput : EditText
    private lateinit var textResult : TextView
    private lateinit var textResultType : TextView
    private lateinit var selectedUnit : String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val df = DecimalFormat("#.##")//Decimal formatter
        selectType = findViewById(R.id.selectType)
        textType = findViewById(R.id.textType)
        editInput = findViewById(R.id.editInput)
        textResult = findViewById(R.id.textResult)
        textResultType = findViewById(R.id.textResultType)

        selectedUnit = "Fahrenheit"


        selectType.setOnClickListener() {
            showAlertDialog()

        }

        editInput.addTextChangedListener() {
            val resultText: String
            var inputVal = editInput.text.toString()
            if (inputVal.isNotEmpty()) {
                var doubleInput = inputVal.toDouble()
                if(selectedUnit == "Fahrenheit"){
                    resultText = df.format((doubleInput - 32) * 5 / 9)
                    textResultType.text = "Celsius"
                }else{
                    //(0°C × 9/5) + 32
                    resultText = df.format((doubleInput *9/5)+32)
                    textResultType.text = "Fahrenheit"
                }

                textResult.text = resultText
            }

        }

    }

    private fun showAlertDialog() {
        val alertDialog: Builder = Builder(this@MainActivity)
        alertDialog.setTitle("Select Unit") //Setting title for alertBox
        val items = arrayOf("Fahrenheit", "Celsius")
        val checkedItem = -1
        alertDialog.setSingleChoiceItems(items, checkedItem,
            DialogInterface.OnClickListener { dialog, which ->
                this.selectedUnit = items[which]
                textType.text = items[which]
            })
        alertDialog.setPositiveButton(
            android.R.string.ok,
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}