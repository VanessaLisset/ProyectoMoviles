package com.example.proyectofinal_moviles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _carrito = MutableLiveData<List<producto>>(emptyList())
    val carrito: LiveData<List<producto>> = _carrito

    fun agregarAlCarrito(producto: producto) {
        val listaActual = _carrito.value?.toMutableList() ?: mutableListOf()
        listaActual.add(producto)
        _carrito.value = listaActual
    }

    fun eliminarDelCarrito(producto: producto) {
        val currentCart = carrito.value ?: emptyList()
        val updatedCart = currentCart.filter { it.id != producto.id }
        _carrito.value = updatedCart
    }
}


