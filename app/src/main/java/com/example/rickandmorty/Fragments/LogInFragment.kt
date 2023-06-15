package com.example.rickandmorty.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogIn.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val surname = binding.etSurname.text.toString().trim()
            val date = binding.etDate.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            if (name.isNotEmpty() && surname.isNotEmpty() && date.isNotEmpty() && email.isNotEmpty()) {
                findNavController().navigate(R.id.action_logInFragment_to_rickAndMortyCharacters)
            } else {
                Toast.makeText(requireContext(), "Enter all the data", Toast.LENGTH_LONG).show()
            }

        }

    }
}