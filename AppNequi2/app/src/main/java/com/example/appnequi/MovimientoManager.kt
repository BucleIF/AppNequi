package com.example.appnequi

object MovimientoManager {
    val historialMovimientos = mutableListOf<Movimiento>()

    fun agregarMovimiento(movimiento: Movimiento){
        historialMovimientos.add(movimiento)
    }
}