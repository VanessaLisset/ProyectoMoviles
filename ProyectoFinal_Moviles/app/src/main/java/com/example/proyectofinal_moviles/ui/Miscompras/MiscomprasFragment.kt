package com.example.proyectofinal_moviles.ui.Miscompras

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal_moviles.databinding.ActivityComprasBinding

class MiscomprasFragment : Fragment() {

    private var _binding: ActivityComprasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val MiscomprasViewModel =
            ViewModelProvider(this).get(MiscomprasViewModel::class.java)

        _binding = ActivityComprasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textComp
        MiscomprasViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}