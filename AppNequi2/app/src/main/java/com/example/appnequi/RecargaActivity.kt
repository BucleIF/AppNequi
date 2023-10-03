package com.example.appnequi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar

class RecargaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recarga)

        val bundle = intent.extras
        val saldo = bundle?.getString("SaldoActual")

        val envio = findViewById<EditText>(R.id.etEnvio)
        val boton = findViewById<Button>(R.id.btn_meter)




        boton.setOnClickListener {
            var saldoNuevo = saldo?.toInt() ?: 0
            saldoNuevo += envio.text.toString().toInt()

            val RecargarMovimiento = Movimiento("Recarga Dinero", envio.text.toString(), fechaActual())
            MovimientoManager.agregarMovimiento(RecargarMovimiento)





            val enviar = Intent(this, MenuActivity::class.java)
            enviar.putExtra("SaldoNuevo", saldoNuevo.toString())
            startActivity(enviar)


            Toast.makeText(this, "Operacion realizada con exito.", Toast.LENGTH_LONG).show()
        }

    }


    private fun fechaActual(): String {
        val calendario = Calendar.getInstance()
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        return formatoFecha.format(calendario.time)

    }
}



