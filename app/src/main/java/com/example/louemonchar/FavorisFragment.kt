package com.example.louemonchar

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class FavorisFragment : Fragment() {
    private lateinit var favorisViewModel: ListeFavoris
    private lateinit var gridView: GridView
    private lateinit var aucunFavoriMessageTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favoris, container, false)
        favorisViewModel = ViewModelProvider(requireActivity()).get(ListeFavoris::class.java)
        gridView = view.findViewById(R.id.gridView)
        aucunFavoriMessageTextView = view.findViewById(R.id.aucunFavoriMessageTextView)

        val modelesFavori = favorisViewModel.modelesFavoris
        val adapter = GridViewTexte(requireContext(), android.R.layout.simple_list_item_1, favorisViewModel.modelesFavoris)
        gridView.adapter = adapter

        if (modelesFavori.isNullOrEmpty()) {
            aucunFavoriMessageTextView.visibility = View.VISIBLE
        }else {
            aucunFavoriMessageTextView.visibility = View.GONE
        }


        val effacerButton: Button = view.findViewById(R.id.btnEffacer)
        effacerButton.setOnClickListener {
            effacerDernierModeleFavori()
        }
        return view
    }

    private fun effacerDernierModeleFavori() {
        if (favorisViewModel.modelesFavoris.isNotEmpty()) {
            favorisViewModel.modelesFavoris.removeAt(favorisViewModel.modelesFavoris.size - 1)

            (gridView.adapter as? GridViewTexte)?.updateData(favorisViewModel.modelesFavoris)

            Toast.makeText(requireContext(), "Dernier modèle favori effacé", Toast.LENGTH_SHORT).show()

            // Vérifier si la liste des favoris est maintenant vide et afficher le message approprié
            if (favorisViewModel.modelesFavoris.isEmpty()) {
                aucunFavoriMessageTextView.visibility = View.VISIBLE
            }
        } else {
            // Afficher un message si la liste des favoris est déjà vide
            Toast.makeText(requireContext(), "Aucun modèle favori à effacer", Toast.LENGTH_SHORT).show()
        }
    }
}


