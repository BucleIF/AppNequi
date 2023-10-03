package com.example.appnequi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText




class RetiraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retira)

        val bundle = intent.extras
        val saldo = bundle?.getString("saldoDialog")


        val code1 = findViewById<EditText>(R.id.rombo5)
        val code2 = findViewById<EditText>(R.id.rombo6)
        val code3 = findViewById<EditText>(R.id.rombo7)
        val code4 = findViewById<EditText>(R.id.rombo8)

        val btn = findViewById<Button>(R.id.btnRetiro)

        val num = (Math.random() * 10).toInt().toString()
        val num2 = (Math.random() * 10).toInt().toString()
        val num3 = (Math.random() * 10).toInt().toString()
        val num4 = (Math.random() * 10).toInt().toString()
        val variables = num + num2 + num3 + num4

        val editableValue: Editable = Editable.Factory.getInstance().newEditable(num)
        val editableValue2: Editable = Editable.Factory.getInstance().newEditable(num2)
        val editableValue3: Editable = Editable.Factory.getInstance().newEditable(num3)
        val editableValue4: Editable = Editable.Factory.getInstance().newEditable(num4)

        code1.text = editableValue
        code2.text = editableValue2
        code3.text = editableValue3
        code4.text = editableValue4


        btn.setOnClickListener {
            val intent = Intent(this, CajeroActivity::class.java)
            intent.putExtra("Codigo", variables.toInt())
            intent.putExtra("SaldoActual", saldo.toString())
            startActivity(intent)
        }


    }
}

