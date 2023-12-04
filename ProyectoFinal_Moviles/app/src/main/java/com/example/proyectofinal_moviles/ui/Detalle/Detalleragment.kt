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
        binding.txtproductModelo.text = productoEncontrado?.modelo
        binding.txtproductDescri.text = productoEncontrado?.descrip
        binding.txtproductPrice.text = productoEncontrado?.precio

        return root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val starCountTextView = view.findViewById<TextView>(R.id.tvStarCount) // Añade esta línea para encontrar el TextView

        val productId = obtenerIdDelProducto() // Este método es hipotético, debes reemplazarlo con tu lógica.

        val sharedPref = activity?.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

        val savedRating = sharedPref?.getFloat("RatingForProduct_$productId", -1f) ?: -1f

        if (savedRating != -1f) {
            ratingBar.rating = savedRating
            starCountTextView.text = savedRating.toString() // Actualiza el TextView con la calificación guardada
        }

        ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ ->
            Toast.makeText(context, "Calificaste con $rating estrellas", Toast.LENGTH_SHORT).show()
            starCountTextView.text = rating.toString()

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
            producto(R.drawable.prod1, "Caterpillar D8T Bulldozer", "Marca: Caterpillar", "Modelo: D8T", "$1000", 1, "Un potente bulldozer para trabajos de construcción pesada y minería."),
            producto(R.drawable.prod2, "Liebherr LTM 11200-9.1 Grúa Móvil", "Marca: Liebherr", "Modelo: LTM 11200-9.1", "$2000", 2, "Una de las grúas móviles más grandes y potentes, utilizada en construcción y montaje de estructuras."),
            producto(R.drawable.prod3, "Komatsu PC8000-6 Excavadora", "Marca: Komatsu", "Modelo: PC8000-6", "$3000", 3, "Excavadora de minería de alto rendimiento para operaciones de gran escala."),
            producto(R.drawable.prod3, "Volvo A60H Dumper Articulado", "Marca: Volvo", "Modelo: A60H", "$500", 4, "Camión dumper para transporte de grandes cantidades de material en construcción y minería."),
            producto(R.drawable.prod3, "John Deere 9620RX Tractor Agrícola", "Marca: John Deere", "Modelo: 9620RX", "$5000", 5, "Tractor de alta potencia para trabajos agrícolas extensivos."),
            producto(R.drawable.prod3, "Mack Granite Camión de Basura", "Marca: Mack Trucks", "Modelo: Granite", "$3424", 6, "Camión robusto diseñado para la recolección y transporte de residuos."),
            producto(R.drawable.prod3, "Hitachi ZX870 Excavadora de Orugas", "Marca: Hitachi", "Modelo: ZX870", "$5934", 7, "Excavadora de gran tamaño para construcción y proyectos de ingeniería civil."),
            producto(R.drawable.prod3, "MAN TGX 41.640 Camión de Transporte Pesado", "Marca: MAN", "Modelo: TGX 41.640", "$900", 8, "Camión para transporte de cargas pesadas y sobredimensionadas."),
            producto(R.drawable.prod3, "Terex Demag AC 500-2 Grúa Todo Terreno", "Marca: Terex Demag", "Modelo: AC 500-2", "$508", 9, "Grúa móvil con capacidades de elevación excepcionales, adecuada para terrenos difíciles."),
            producto(R.drawable.prod3, "Bobcat T870 Cargador Compacto de Orugas", "Marca: Bobcat", "Modelo: T870", "$400", 10, "Cargador compacto de alto rendimiento para construcción y paisajismo."),
            producto(R.drawable.prod3, "Doosan DA40 Camión Articulado", "Marca: Doosan", "Modelo: DA40", "$6509", 11, "Camión articulado para operaciones de minería y construcción de carreteras."),
            producto(R.drawable.prod3, "JCB 220X Excavadora de Orugas", "Marca: JCB", "Modelo: 220X", "$837", 12, "Excavadora robusta y eficiente para proyectos de construcción de mediana a gran escala."),
            producto(R.drawable.prod3, "Case IH Magnum 380 CVX Tractor", "Marca: Case IH", "Modelo: Magnum 380 CVX", "$6721", 13, "Tractor de gran potencia para tareas agrícolas exigentes."),
            producto(R.drawable.prod3, "Kenworth W990 Camión de Largo Recorrido", "Marca: Kenworth", "Modelo: W990", "$1092", 14, "Camión diseñado para transporte de larga distancia, combinando comodidad y rendimiento."),
            producto(R.drawable.prod3, "Bell B45E Camión Dumper Articulado", "Marca: Bell Equipment", "Modelo: B45E", "$922", 15, "Camión dumper para el transporte eficiente de materiales en minas y grandes proyectos de construcción.")
        )
        return productos.find { it.id == id_producto }
    }

}