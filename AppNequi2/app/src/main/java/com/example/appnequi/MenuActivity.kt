package com.example.appnequi


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.VirtualLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MenuActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btn1 = findViewById<ImageButton>(R.id.btnServicios)
        val btn2 = findViewById<ImageButton>(R.id.btnEnvia)
        val btn3 = findViewById<ImageButton>(R.id.btnRecarga)
        val btn4 = findViewById<ImageButton>(R.id.btnRetirar)

        val txtSaldo = findViewById<TextView>(R.id.txtSaldo)
        val bundle = intent.extras
        val saldoNuevo = bundle?.getString("SaldoNuevo") ?: "0"
        txtSaldo.text = saldoNuevo

        val btnHistory = findViewById<ImageButton>(R.id.btnHistorial)

        val fabMain = findViewById<FloatingActionButton>(R.id.fabMain)
        val hiddenButtonsContainer = findViewById<LinearLayout>(R.id.hiddenButtonsContainer)

        val txtservi = findViewById<TextView>(R.id.txtBtnServicios)
        val txtenvi = findViewById<TextView>(R.id.txtBtnEnviar)
        val txtreca = findViewById<TextView>(R.id.txtBtnRecargar)
        val txtreti = findViewById<TextView>(R.id.txtBtnRetirar)
        val user = findViewById<ImageView>(R.id.imgUser)
        val txtUser = findViewById<TextView>(R.id.txtUser)
        val txtDisponible = findViewById<TextView>(R.id.txtDisponible)


        val layout = findViewById<ConstraintLayout>(R.id.ctLayoutMenu)



        var isHidden = true

        fabMain.setOnClickListener{
            Thread.sleep(500)

            if (isHidden) {
                layout.setBackgroundResource(R.color.backTransparent)
                hiddenButtonsContainer.visibility = View.VISIBLE
                txtservi.visibility = View.VISIBLE
                txtenvi.visibility = View.VISIBLE
                txtreca.visibility = View.VISIBLE
                txtreti.visibility = View.VISIBLE

                txtDisponible.visibility = View.GONE
                txtSaldo.visibility = View.GONE
                btnHistory.visibility = View.GONE
                user.visibility = View.GONE
                txtUser.visibility = View.GONE

            }   else {
                layout.setBackgroundResource(R.drawable.backgrounmenu)
                hiddenButtonsContainer.visibility = View.GONE
                txtservi.visibility = View.GONE
                txtenvi.visibility = View.GONE
                txtreca.visibility = View.GONE
                txtreti.visibility = View.GONE

                txtDisponible.visibility = View.VISIBLE
                txtSaldo.visibility = View.VISIBLE
                btnHistory.visibility = View.VISIBLE
                user.visibility = View.VISIBLE
                txtUser.visibility = View.VISIBLE
            }
            isHidden = !isHidden
        }


        btn1.setOnClickListener {

            val intent = Intent(this, ServiciosActivity::class.java)
            intent.putExtra("saldoActual", txtSaldo.text.toString())
            startActivity(intent)

        }


        btn2.setOnClickListener {

            val intent = Intent(this, EnviaActivity::class.java)
            intent.putExtra("saldoActual", txtSaldo.text.toString())
            startActivity(intent)


        }


        btn3.setOnClickListener {
            val intent = Intent(this, RecargaActivity::class.java)
            intent.putExtra("SaldoActual", txtSaldo.text.toString())
            startActivity(intent)

        }


        btn4.setOnClickListener {

            val saldo = txtSaldo.text.toString()
            showWithdrawOptionsDialog(saldo)

        }








        btnHistory.setOnClickListener {
            val intent = Intent(this, HistorialActivity::class.java)
            startActivity(intent)

        }

    }



    private fun showWithdrawOptionsDialog(Saldo: String) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.ventana_retirar, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)

        val dialog = builder.create()
        dialog.show()

        val btnBancolombia = dialogView.findViewById<Button>(R.id.btnBancolombia)


        btnBancolombia.setOnClickListener {
            // Acciones para la opción de retiro en cajero automático
            Thread.sleep(2000)
            val intent = Intent(this, RetiraActivity::class.java)
            intent.putExtra("saldoDialog", Saldo)
            startActivity(intent)

            dialog.dismiss()
        }


    }




}


