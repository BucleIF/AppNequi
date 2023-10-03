package com.example.appnequi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar


class CajeroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cajero)

        //Recibimos el saldo actual y el codigo de RetiraActivity
        val bundle = intent.extras
        val saldo = bundle?.getString("SaldoActual")

        val bundle2 = intent.extras
        val codigo = bundle2?.getInt("Codigo")


        val cuantoRetirar =  findViewById<EditText>(R.id.etCuantoRetirar)
        val confirmar = findViewById<Button>(R.id.btnCajero)

        val rombo9 = findViewById<EditText>(R.id.rombo9)
        val rombo10 = findViewById<EditText>(R.id.rombo10)
        val rombo11 = findViewById<EditText>(R.id.rombo11)
        val rombo12 = findViewById<EditText>(R.id.rombo12)

        val editTexts = listOf(rombo9, rombo10, rombo11, rombo12)

        //Cambiar de foco automaticamente
        editTexts.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (count == 1 && index < editTexts.size - 1) {
                        editTexts[index + 1].requestFocus()
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}

            })
        }


        confirmar.setOnClickListener {
            val rombo9 = findViewById<EditText>(R.id.rombo9).text.toString()
            val rombo10 = findViewById<EditText>(R.id.rombo10).text.toString()
            val rombo11 = findViewById<EditText>(R.id.rombo11).text.toString()
            val rombo12 = findViewById<EditText>(R.id.rombo12).text.toString()
            val inputCode = rombo9 + rombo10 + rombo11 + rombo12

            if (rombo9.isEmpty() || rombo10.isEmpty() || rombo11.isEmpty() || rombo12.isEmpty()){
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()

            }else if (inputCode.toInt() != codigo){
                Toast.makeText(this, "Codigo Incorrecto.. Por favor vuelva a intentarlo.", Toast.LENGTH_SHORT).show()

            }
            else {
                val cantidadInt = cuantoRetirar.text.toString().toIntOrNull()

                if (cantidadInt != null && cantidadInt > 0) {
                    val saldoActual = saldo?.toInt() ?: 0

                    if (cantidadInt >= 10000) {
                        if (cantidadInt <= saldoActual) {
                            val nuevoSaldo = saldoActual - cantidadInt

                            Toast.makeText(this, "Dinero Retirado Exitosamente.", Toast.LENGTH_SHORT).show()
                            val retiroMovimiento = Movimiento("Retiro Dinero", cuantoRetirar.text.toString(), fechaActual())
                            MovimientoManager.agregarMovimiento(retiroMovimiento)




                            val sending = Intent(this, MenuActivity::class.java)
                            sending.putExtra("SaldoNuevo", nuevoSaldo.toString())
                            startActivity(sending)

                        } else {
                            Toast.makeText(this, "Saldo insuficiente.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "La cantidad mínima para retirar es de 10000.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Ingrese una cantidad de dinero válida para retirar.", Toast.LENGTH_SHORT).show()
                }
            }


        }


    }

    private fun fechaActual(): String {
        val calendario = Calendar.getInstance()
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        return formatoFecha.format(calendario.time)

    }
}