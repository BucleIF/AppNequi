package com.example.appnequi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovimientoAdapter(private val movimientos: List<Movimiento>) : RecyclerView.Adapter<MovimientoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tipoTextView: TextView = itemView.findViewById(R.id.tipoTextView)
        val montoTextView: TextView = itemView.findViewById(R.id.montoTextView)
        val fechaTextView: TextView = itemView.findViewById(R.id.fechaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movimiento, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movimiento = movimientos[position]
        holder.tipoTextView.text = movimiento.tipo
        holder.montoTextView.text = "Monto: ${movimiento.monto}"
        holder.fechaTextView.text = "Fecha: ${movimiento.fecha}\n-----------------"
    }

    override fun getItemCount(): Int {
        return movimientos.size
    }
}
