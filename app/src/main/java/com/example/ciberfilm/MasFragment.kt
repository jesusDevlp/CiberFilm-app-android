package com.example.ciberfilm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ciberfilm.databinding.ActivityHomeBinding
import com.example.ciberfilm.databinding.FragmentInicioBinding
import com.example.ciberfilm.databinding.FragmentMasBinding

class MasFragment : Fragment() {
    private var _binding : FragmentMasBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMasBinding.inflate(inflater, container, false)
        val root : View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatBtn.setOnClickListener{
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}