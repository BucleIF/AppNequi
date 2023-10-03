package com.example.appnequi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar

class EnviaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envia)


        val button = findViewById<Button>(R.id.btnEnviar)
        val cantidadenviar = findViewById<EditText>(R.id.etCuanto)
        val bundle = intent.extras
        val saldo = bundle?.getString("saldoActual")

        button.setOnClickListener {
            val userInput = findViewById<EditText>(R.id.etCelular).text.toString()
            val cantidad = cantidadenviar.text.toString()

            if (userInput.isEmpty() || cantidad.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
            } else if (userInput.length != 10) {
                Toast.makeText(this, "Debes ingresar exactamente 10 dígitos en el número de cuenta.", Toast.LENGTH_SHORT).show()
            } else {
                val cantidadInt = cantidad.toIntOrNull()

                if (cantidadInt != null && cantidadInt > 0) {
                    val saldoActual = saldo?.toInt() ?: 0

                    if (cantidadInt >= 5000) {
                        if (cantidadInt <= saldoActual) {
                            val nuevoSaldo = saldoActual - cantidadInt

                            Toast.makeText(this, "Dinero Enviado Exitosamente.", Toast.LENGTH_SHORT).show()
                            val enviaMovimiento = Movimiento("Envio Dinero", cantidad, fechaActual())
                            MovimientoManager.agregarMovimiento(enviaMovimiento)



                            val sending = Intent(this, MenuActivity::class.java)
                            sending.putExtra("SaldoNuevo", nuevoSaldo.toString())
                            startActivity(sending)
                        } else {
                            Toast.makeText(this, "No tienes suficiente saldo para enviar esa cantidad.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "La cantidad mínima para enviar es de 5000.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Ingrese una cantidad de dinero válida para enviar.", Toast.LENGTH_SHORT).show()
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