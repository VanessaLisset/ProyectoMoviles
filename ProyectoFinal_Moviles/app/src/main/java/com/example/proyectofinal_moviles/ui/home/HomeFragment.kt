package com.example.proyectofinal_moviles.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal_moviles.Favorito
import com.example.proyectofinal_moviles.FavoritosAdapter
import com.example.proyectofinal_moviles.ProductAdapter
import com.example.proyectofinal_moviles.R
import com.example.proyectofinal_moviles.databinding.FragmentHomeBinding
import com.example.proyectofinal_moviles.producto
import com.example.proyectofinal_moviles.ui.Detalle.Detalleragment
import com.example.proyectofinal_moviles.ui.Favoritos.FavoritosViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val listaFavoritos: MutableList<Favorito> = mutableListOf()
    private lateinit var favoritosAdapter: FavoritosAdapter
    private val favoritosViewModel: FavoritosViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        favoritosAdapter = FavoritosAdapter(listaFavoritos) { position ->
            eliminarFavorito(position)
        }

        val productos = listOf(
            producto(R.drawable.prod1, "Caterpillar D8T Bulldozer", "Marca: Caterpillar", "Modelo: D8T", "$1000", 1, "Un potente bulldozer para trabajos de construcción pesada y minería."),
            producto(R.drawable.prod2, "Liebherr LTM 11200-9.1", "Marca: Liebherr", "Modelo: LTM 11200", "$2000", 2, "Una de las grúas móviles más grandes y potentes, utilizada en construcción y montaje de estructuras."),
            producto(R.drawable.prod3, "Komatsu PC8000-6 Excavadora", "Marca: Komatsu", "Modelo: PC8000-6", "$3000", 3, "Excavadora de minería de alto rendimiento para operaciones de gran escala."),
            producto(R.drawable.prod4, "Volvo A60H Dumper Articulado", "Marca: Volvo", "Modelo: A60H", "$500", 4, "Camión dumper para transporte de grandes cantidades de material en construcción y minería."),
            producto(R.drawable.prod5, "John Deere 9620RX Tractor", "Marca: John", "Modelo: 9620RX", "$5000", 5, "Tractor de alta potencia para trabajos agrícolas extensivos."),
            producto(R.drawable.prod6, "Mack Granite Camión de Basura", "Marca: Mack", "Modelo: Granite", "$3424", 6, "Camión robusto diseñado para la recolección y transporte de residuos."),
            producto(R.drawable.prod7, "Hitachi ZX870 Excavadora", "Marca: Hitachi", "Modelo: ZX870", "$5934", 7, "Excavadora de gran tamaño para construcción y proyectos de ingeniería civil."),
            producto(R.drawable.prod8, "MAN TGX 41.640 Camión", "Marca: MAN", "Modelo: TGX 41.640", "$900", 8, "Camión para transporte de cargas pesadas y sobredimensionadas."),
            producto(R.drawable.prod9, "Terex Demag AC 500-2", "Marca: Terex", "Modelo: AC 500-2", "$508", 9, "Grúa móvil con capacidades de elevación excepcionales, adecuada para terrenos difíciles."),
            producto(R.drawable.prod10, "Bobcat T870 Cargador", "Marca: Bobcat", "Modelo: T870", "$400", 10, "Cargador compacto de alto rendimiento para construcción y paisajismo."),
            producto(R.drawable.prod3, "Doosan DA40 Camión", "Marca: Doosan", "Modelo: DA40", "$6509", 11, "Camión articulado para operaciones de minería y construcción de carreteras."),
            producto(R.drawable.prod1, "JCB 220X Excavadora", "Marca: JCB", "Modelo: 220X", "$837", 12, "Excavadora robusta y eficiente para proyectos de construcción de mediana a gran escala."),
            producto(R.drawable.prod13, "Case IH Magnum 380", "Marca: Case IH", "Modelo: Magnum", "$6721", 13, "Tractor de gran potencia para tareas agrícolas exigentes."),
            producto(R.drawable.prod4, "Kenworth W990 Camión", "Marca: Kenworth", "Modelo: W990", "$1092", 14, "Camión diseñado para transporte de larga distancia, combinando comodidad y rendimiento."),
            producto(R.drawable.prod15, "Bell B45E Camión Dumper", "Marca: Bell", "Modelo: B45E", "$922", 15, "Camión dumper para el transporte eficiente de materiales en minas y grandes proyectos de construcción.")
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ProductAdapter(
                productos,
                { producto -> navegarADetalleFragment(producto) }, // función onClick
                { producto -> onFavoritoClick(producto) } // función onFavoritoClick
            )
        }
        return root
    }
    private fun onFavoritoClick(producto: producto) {
        val favorito = Favorito(
            producto.id,
            producto.nombre,
            producto.marca,
            producto.modelo,
            producto.precio,
            producto.imagen
        )
        favoritosViewModel.agregarFavorito(favorito)


        // Opcional: Puedes mostrar un mensaje o realizar otras acciones relacionadas con los favoritos.
        val mensaje = "Añadido a favoritos: ${producto.nombre}"
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun eliminarFavorito(position: Int) {
        // Aquí debes eliminar el favorito de la lista de favoritos en tu ViewModel
        favoritosViewModel.eliminarFavorito(position)
    }

    private fun navegarADetalleFragment(producto: producto) {
        val detalleFragment = Detalleragment().apply {
            arguments = Bundle().apply {
                putString("claveProducto", producto.id.toString())
            }
        }

        val navController = findNavController()
        navController.navigate(R.id.detalleragment, detalleFragment.arguments)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
