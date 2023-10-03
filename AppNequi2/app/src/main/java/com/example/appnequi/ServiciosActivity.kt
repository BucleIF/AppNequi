package com.example.appnequi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar

class ServiciosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)

        val bundle = intent.extras
        val saldo = bundle?.getString("saldoActual")

        val button = findViewById<Button>(R.id.pagar)
        val cantidadPagar = findViewById<EditText>(R.id.cantidad_pagar)
        val radioGroup = findViewById<RadioGroup>(R.id.radiogroup)

        button.setOnClickListener {
            val cantidad = cantidadPagar.text.toString()
            val selectedRadioId = radioGroup.checkedRadioButtonId

            if (cantidad.isEmpty() || selectedRadioId == -1) {
                Toast.makeText(this, "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT).show()
            } else {
                val cantidadInt = cantidad.toIntOrNull()

                if (cantidadInt != null && cantidadInt > 0) {
                    val saldoActual = saldo?.toInt() ?: 0

                    if (cantidadInt >= 10000) {
                        if (cantidadInt <= saldoActual) {
                            val nuevoSaldo = saldoActual - cantidadInt

                            Toast.makeText(this, "Servicio Pagado Exitosamente.", Toast.LENGTH_SHORT).show()
                            val servicioMovimiento = Movimiento("Pago Servicio", cantidad, fechaActual())
                            MovimientoManager.agregarMovimiento(servicioMovimiento)





                            val sending = Intent(this, MenuActivity::class.java)
                            sending.putExtra("SaldoNuevo", nuevoSaldo.toString())
                            startActivity(sending)
                        } else {
                            Toast.makeText(this, "No tienes suficiente saldo para pagar el servicio.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "La cantidad mínima de pago es de 10000.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Ingrese una cantidad válida de pago.", Toast.LENGTH_SHORT).show()
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