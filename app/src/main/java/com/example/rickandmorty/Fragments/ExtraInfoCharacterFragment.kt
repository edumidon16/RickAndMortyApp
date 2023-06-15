package com.example.rickandmorty.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmorty.API.ApiService
import com.example.rickandmorty.API.CharacterInfo
import com.example.rickandmorty.databinding.FragmentExtraInfoCharacterBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExtraInfoCharacterFragment : Fragment() {

    private var _binding: FragmentExtraInfoCharacterBinding?=null
    private val binding get() = _binding!!

    private var id:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExtraInfoCharacterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {bundle ->
            id = bundle.getInt("id")
        }
        characterInfo(id)
    }

    private fun characterInfo(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val characterInfo = getRetrofit().create(ApiService::class.java).getCharacterInfo(id)
            if(characterInfo.isSuccessful){
                Log.i("edu", "funciona")
                val response = characterInfo.body()
           //     if(response != null){
                    Log.i("edu", response.toString())
                    activity?.runOnUiThread {
                       createUI(characterInfo.body()!!)
                    }
       //         }

            }else{
                Log.i("edu", "ERROR :(")
            }
        }

    }

    private fun createUI(character: CharacterInfo) {

        Picasso.get().load(character.characterImage).into(binding.iwCharacter)
        binding.tvName.text = "Name: ${character.name}"
        binding.tvStatus.text = "Status: ${character.characterStatus}"
        binding.tvSpecies.text = "Specie: ${character.characterSpecies}"
        binding.tvGender.text = "Gender: ${character.characterGender}"
       binding.tvOrigin.text = "Origin: ${character.characterOriginName.name}"
       binding.tvLocation.text = "Location: ${character.characterLocationName.name}"

    }

    private fun getRetrofit(): Retrofit {
        val Retrofit = Retrofit.Builder().baseUrl("https://rickandmortyapi.com/").addConverterFactory(
            GsonConverterFactory.create()).build()
        return Retrofit
    }

}