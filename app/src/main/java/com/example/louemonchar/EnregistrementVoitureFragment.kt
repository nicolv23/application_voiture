package com.example.louemonchar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.louemonchar.modèle.ListeVoituresEnregistres

class EnregistrementVoitureFragment : Fragment() {
    private lateinit var enregistrementViewModel: ListeVoituresEnregistres
    private lateinit var gridView: GridView
    private lateinit var aucunEnregistrementMessage: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_enregistrement_voitures, container, false)
        enregistrementViewModel = ViewModelProvider(requireActivity()).get(ListeVoituresEnregistres::class.java)
        gridView = view.findViewById(R.id.gridView)
        aucunEnregistrementMessage = view.findViewById(R.id.aucunFavoriMessageTextView)

        val listeEnregistrementVoiture = enregistrementViewModel.voituresEnregistres
        val adapter = GridViewTexte(requireContext(), android.R.layout.simple_list_item_1, enregistrementViewModel.voituresEnregistres)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            val modeleSelectionne = enregistrementViewModel.voituresEnregistres[position]
            if (modeleSelectionne == "Hyundai Elantra") {
                val action = EnregistrementVoitureFragmentDirections.actionEnregistrementsFragmentToÉcranDétail()
                findNavController().navigate(action)
            }
            else{
                Toast.makeText(requireContext(), "Ce modèle ne peut pas s'afficher en ce moment", Toast.LENGTH_SHORT).show()
            }
        }

        if (listeEnregistrementVoiture.isNullOrEmpty()) {
            aucunEnregistrementMessage.visibility = View.VISIBLE
        }else {
            aucunEnregistrementMessage.visibility = View.GONE
        }


        val effacerButton: Button = view.findViewById(R.id.btnEffacer)
        effacerButton.setOnClickListener {
            effacerDernierEnregistrementVoiture()
        }
        return view
    }

    private fun effacerDernierEnregistrementVoiture() {
        if (enregistrementViewModel.voituresEnregistres.isNotEmpty()) {
            enregistrementViewModel.voituresEnregistres.removeAt(enregistrementViewModel.voituresEnregistres.size - 1)

            (gridView.adapter as? GridViewTexte)?.updateData(enregistrementViewModel.voituresEnregistres)

            Toast.makeText(requireContext(), "Dernier modèle enregistré effacé", Toast.LENGTH_SHORT).show()

            // Vérifier si la liste des favoris est maintenant vide et afficher le message approprié
            if (enregistrementViewModel.voituresEnregistres.isEmpty()) {
                aucunEnregistrementMessage.visibility = View.VISIBLE
            }
        } else {
            // Afficher un message si la liste des favoris est déjà vide
            Toast.makeText(requireContext(), "Aucun enregistrement à effacer", Toast.LENGTH_SHORT).show()
        }
    }
}


