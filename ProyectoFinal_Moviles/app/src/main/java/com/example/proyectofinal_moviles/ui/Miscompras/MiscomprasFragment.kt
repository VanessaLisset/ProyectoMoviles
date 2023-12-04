package com.example.proyectofinal_moviles.ui.Miscompras
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal_moviles.SharedViewModel
import com.example.proyectofinal_moviles.databinding.ActivityComprasBinding
import com.example.proyectofinal_moviles.producto

class MiscomprasFragment : Fragment() {
    private var _binding: ActivityComprasBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        _binding = ActivityComprasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeCart()
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
    }

    private fun observeCart() {
        sharedViewModel.carrito.observe(viewLifecycleOwner) { updatedCart ->
            cartAdapter.setData(updatedCart)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
