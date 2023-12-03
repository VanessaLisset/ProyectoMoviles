package com.example.proyectofinal_moviles.ui.Historial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal_moviles.databinding.ActivityHistorialBinding
import com.example.proyectofinal_moviles.ui.Historial.HistorialViewModel

class HistorialFragment : Fragment() {

    private var _binding: ActivityHistorialBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val HistorialViewModel =
            ViewModelProvider(this).get(HistorialViewModel::class.java)

        _binding = ActivityHistorialBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHist
        HistorialViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}