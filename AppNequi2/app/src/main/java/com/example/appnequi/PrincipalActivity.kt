package com.example.appnequi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val btn = findViewById<Button>(R.id.btnEntrar)

        btn.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editTextPhone)
            val userInput = editText.text.toString()

            if (userInput.length == 10){

                Toast.makeText(this, "Numero Correcto", Toast.LENGTH_SHORT).show()


                val intent = Intent(this, CodeActivity::class.java)
                startActivity(intent)
            }else {

                editText.error = "El numero de telefono debe tener 10 digitos!."
            }

        }
    }

}