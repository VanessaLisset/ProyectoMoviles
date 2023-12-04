package com.example.proyectofinal_moviles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _carrito = MutableLiveData<MutableList<producto>>(mutableListOf())
    val carrito: LiveData<MutableList<producto>> = _carrito

    fun agregarAlCarrito(producto: producto) {
        val listaActual = _carrito.value ?: mutableListOf()
        listaActual.add(producto)
        _carrito.value = listaActual
    }
}
