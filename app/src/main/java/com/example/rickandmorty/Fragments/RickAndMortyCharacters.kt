package com.example.rickandmorty.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.API.ApiService
import com.example.rickandmorty.R
import com.example.rickandmorty.RickAndMortyAdapter
import com.example.rickandmorty.databinding.FragmentRickAndMortyCharactersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickAndMortyCharacters : Fragment() {

    private lateinit var retrofit: Retrofit
    private lateinit var adapter: RickAndMortyAdapter

    private var _binding: FragmentRickAndMortyCharactersBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRickAndMortyCharactersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        adapter = RickAndMortyAdapter{characterId -> navigateToDetail(characterId)}
        binding.rvRickAndMortyCharacters.setHasFixedSize(true)
        binding.rvRickAndMortyCharacters.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRickAndMortyCharacters.adapter = adapter
    }
    private fun searchByName(query: String){
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getRickAndMorty(query)
            if(myResponse.isSuccessful){
                Log.i("edu", "funciona")
                val response = myResponse.body()
                if(response != null){
                    Log.i("edu", response.toString())
                    activity?.runOnUiThread {
                        adapter.updateList(response.charactersData)
                        binding.progressBar.isVisible = false
                    }
                }

            }else{
                Log.i("edu", "ERROR :(")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        val Retrofit = Retrofit.Builder().baseUrl("https://rickandmortyapi.com/").addConverterFactory(
            GsonConverterFactory.create()).build()
        return Retrofit
    }

    private fun navigateToDetail(id:Int){
        findNavController().navigate(R.id.action_rickAndMortyCharacters_to_extraInfoCharacterFragment, bundleOf("id" to id))
    }

}