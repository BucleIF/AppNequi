package com.example.appnequi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code)

        val btn = findViewById<Button>(R.id.btnEntrar2)
        val rombo1 = findViewById<EditText>(R.id.rombo1)
        val rombo2 = findViewById<EditText>(R.id.rombo2)
        val rombo3 = findViewById<EditText>(R.id.rombo3)
        val rombo4 = findViewById<EditText>(R.id.rombo4)




        val editTexts = listOf(rombo1, rombo2, rombo3, rombo4)



        editTexts.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (count == 1 && index < editTexts.size - 1) {
                        editTexts[index + 1].requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    val currentText = s.toString()
                    if (currentText.isNotEmpty()) {
                        editText.removeTextChangedListener(this)
                        editText.setText("*")
                        editText.addTextChangedListener(this)
                    }
                }
            })

            editText.setOnFocusChangeListener{ _, hasFocus ->
                if (hasFocus) {
                    editText.text.clear()
                }
            }
        }

        btn.setOnClickListener {
            val rombo1 = findViewById<EditText>(R.id.rombo1).text.toString()
            val rombo2 = findViewById<EditText>(R.id.rombo2).text.toString()
            val rombo3 = findViewById<EditText>(R.id.rombo3).text.toString()
            val rombo4 = findViewById<EditText>(R.id.rombo4).text.toString()

            if (rombo1.isEmpty() || rombo2.isEmpty() || rombo3.isEmpty() || rombo4.isEmpty()){
                Toast.makeText(this, "Por favor Completa todos los campos", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "Pin Correcto", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }







        }


    }
}