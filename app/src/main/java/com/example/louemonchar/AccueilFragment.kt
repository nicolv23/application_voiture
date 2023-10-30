package com.example.louemonchar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

class AccueilFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_accueil, container, false)
        val bouton = view.findViewById<Button>(R.id.bienvenue)

        bouton.setOnClickListener{
            findNavController().navigate(R.id.action_accueilFragment_to_marquesAuto)
            setToolbarTitle(getString(R.string.fragment_marques_auto)) // Changer le titre de la barre d'outils
        }
        return view
    }

    private fun setToolbarTitle(title: String) {
        // Changer le titre de la barre d'outils ou de toute autre vue appropriée
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }
}
