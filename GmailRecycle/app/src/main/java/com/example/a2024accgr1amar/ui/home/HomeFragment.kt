package com.example.a2024accgr1amar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a2024accgr1amar.EmailAdapter
import com.example.a2024accgr1amar.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = binding.recyclerViewHome
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Configurar el Adapter
        val adapter = EmailAdapter(emptyList())
        recyclerView.adapter = adapter

        // Observar cambios en la lista de ítems
        homeViewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.updateItems(items)
        }

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}