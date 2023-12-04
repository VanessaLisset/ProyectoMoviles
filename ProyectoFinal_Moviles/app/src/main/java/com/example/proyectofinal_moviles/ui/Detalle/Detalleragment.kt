package com.example.proyectofinal_moviles.ui.Detalle

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal_moviles.R
import com.example.proyectofinal_moviles.databinding.ActivityDetalleBinding
import com.example.proyectofinal_moviles.producto

class Detalleragment : Fragment() {
    private var _binding: ActivityDetalleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = ActivityDetalleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val productoIds = arguments?.getString("claveProducto")
        var productoId = 0;
        try {
            productoId = productoIds!!.toInt()
        } catch (ex: NumberFormatException) {
            println("The given string is non-numeric")
        }

        val productoEncontrado = obtenerProducto(productoId)

        binding.txtproductName.text = productoEncontrado?.nombre
        binding.txtproductMarca.text = productoEncontrado?.marca
        binding.txtproductDescri.text = productoEncontrado?.descrip
        binding.txtproductPrice.text = productoEncontrado?.precio



        return root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)


        val productId = obtenerIdDelProducto() // Este método es hipotético, debes reemplazarlo con tu lógica.

        val sharedPref = activity?.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

        val savedRating = sharedPref?.getFloat("RatingForProduct_$productId", -1f) ?: -1f

        if (savedRating != -1f) {
            ratingBar.rating = savedRating

        }

        ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ ->

            Toast.makeText(context, "Calificaste con $rating estrellas", Toast.LENGTH_SHORT).show()


            sharedPref?.edit()?.apply {
                putFloat("RatingForProduct_$productId", rating)
                apply()
            }
        }

    // Inicializar con un valor por defecto o un valor obtenido de SharedPreferences
    var diasARentar = sharedPref?.getInt("DiasARentar_$productId", 1) ?: 1
    binding.txtQuantity.text = diasARentar.toString()

    binding.btnIncrease.setOnClickListener {
        diasARentar++
        binding.txtQuantity.text = diasARentar.toString()
        sharedPref?.edit()?.putInt("DiasARentar_$productId", diasARentar)?.apply()
    }

    // Decrementar los días a rentar
    binding.btnDecrease.setOnClickListener {
        if (diasARentar > 1) {
            diasARentar--
            binding.txtQuantity.text = diasARentar.toString()
            sharedPref?.edit()?.putInt("DiasARentar_$productId", diasARentar)?.apply()
        }
    }
}

    private fun obtenerIdDelProducto(): String {
        return "id_del_producto_actual"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtenerProducto(id_producto: Int): producto? {
        val productos = listOf(
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 1,""),
            producto(R.drawable.prod2, "Excavadora", "Marca: Gazillion Cat Construction", "Modelo Y", "$550", 2,""),
            producto(R.drawable.prod3, "PS L", "Marca: terrenaitor", "Modelo: 2EYplus", "3550", 3,"Esta serie PS L, ha sido especialmente diseñada, para que los operadores hombre caminando, utilicen un equipo seguro y confortable en su trabajo. Gracias a su suave desplazamiento de ascenso y descenso, las operaciones de estiba, se vuelven más seguras y rápidas. "),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 4,""),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 5,""),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 6,""),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 7,""),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 8,""),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 9,""),
        )
        return productos.find { it.id == id_producto }
    }

}