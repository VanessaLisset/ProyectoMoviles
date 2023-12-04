package com.example.proyectofinal_moviles.ui.Miscompras
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal_moviles.R
import com.example.proyectofinal_moviles.SharedViewModel
import com.example.proyectofinal_moviles.databinding.ActivityComprasBinding
import com.example.proyectofinal_moviles.producto

class MiscomprasFragment : Fragment() {
    private var _binding: ActivityComprasBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var cartAdapter: CartAdapter
    private var total: Double = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        _binding = ActivityComprasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeCart()
        updateTotal()

        val btnRentarAhora = binding.root.findViewById<Button>(R.id.btnRentarAhora)

        btnRentarAhora.setOnClickListener {
            val carrito = sharedViewModel.carrito.value ?: emptyList()
            if (carrito.isNotEmpty()) {

                val articulosRentados = carrito.joinToString { it.nombre }
                val mensaje = "Artículos rentados: $articulosRentados, permanece pendiente de tu correo"

                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()

                sharedViewModel.limpiarCarrito()

            } else {
                Toast.makeText(context, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(mutableListOf()) { producto ->
            eliminarDelCarrito(producto)
        }
        binding.rvCart.layoutManager = LinearLayoutManager(context)
        binding.rvCart.adapter = cartAdapter
    }

    private fun eliminarDelCarrito(producto: producto) {
        sharedViewModel.eliminarDelCarrito(producto)
        Toast.makeText(context, "Producto eliminado del carrito", Toast.LENGTH_SHORT).show()
        updateTotal()  // Recalcula y muestra el total cuando se elimina un producto
    }

    private fun observeCart() {
        sharedViewModel.carrito.observe(viewLifecycleOwner) { updatedCart ->
            cartAdapter.setData(updatedCart)
            updateTotal()  // Recalcula y muestra el total cuando cambia el carrito
        }
    }

    private fun updateTotal() {
        var total = 0.0
        for (producto in sharedViewModel.carrito.value ?: emptyList()) {
            val precio = producto.precio.replace("$", "").toDoubleOrNull() ?: 0.0
            val dias = producto.diasARentar
            total += precio * dias
        }

        val totalFormatted = "$${String.format("%.2f", total)}"

        binding.tvTotal.text = "Total: $totalFormatted"
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


