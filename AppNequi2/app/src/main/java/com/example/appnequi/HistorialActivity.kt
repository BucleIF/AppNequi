package com.example.appnequi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class HistorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val emptyTextView = findViewById<TextView>(R.id.emptyTextView)
        val btnClose = findViewById<ImageButton>(R.id.btnCloseHistory)

        val historialMovimientos = MovimientoManager.historialMovimientos

        if (historialMovimientos.isEmpty()) {

            emptyTextView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {

            emptyTextView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

            val layoutManager = LinearLayoutManager(this)
            val adapter = MovimientoAdapter(historialMovimientos)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        }

        btnClose.setOnClickListener{
            finish()
        }
    }
}
