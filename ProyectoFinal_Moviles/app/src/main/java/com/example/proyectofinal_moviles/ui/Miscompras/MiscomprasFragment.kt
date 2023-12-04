package com.example.proyectofinal_moviles.ui.Miscompras

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal_moviles.SharedViewModel
import com.example.proyectofinal_moviles.databinding.ActivityComprasBinding

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
        cartAdapter = CartAdapter(mutableListOf()) // Inicialmente vacío
        binding.rvCart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }
    }

    private fun observeCart() {
        sharedViewModel.carrito.observe(viewLifecycleOwner) { updatedCart ->
            cartAdapter.updateCart(updatedCart)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
