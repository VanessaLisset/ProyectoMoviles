package com.example.proyectofinal_moviles

data class producto(val imagen: Int, val nombre: String, val marca: String, val modelo: String,
                    val precio: String, val id:Int, val descrip: String,
                    var diasARentar: Int = 1,
                    var precioTotal: String = precio

)
