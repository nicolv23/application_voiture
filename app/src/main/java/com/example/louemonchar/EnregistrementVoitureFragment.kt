package com.example.louemonchar

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.modele.ModeleVoiture
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon
import com.example.louemonchar.sourceDonnees.SourceVoitures

class EnregistrementVoitureFragment : Fragment(), ModeleVoiture.ModeleClickListener {
    private lateinit var aucunEnregistrementMessage: TextView
    private lateinit var sourceVoitures: SourceVoitures
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_enregistrement_voitures, container, false)
        sourceVoitures = SourceDeVoituresBidon()
        recyclerView = view.findViewById(R.id.recyclerView)
        aucunEnregistrementMessage = view.findViewById(R.id.aucunFavoriMessageTextView)

        val marqueAuto = requireArguments().getString("marqueAuto") ?: ""
        val listeEnregistrementVoiture = sourceVoitures.getModelesDeVoiture()[marqueAuto] ?: emptyList()

        val adapter = ModeleVoiture(listeEnregistrementVoiture, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if (listeEnregistrementVoiture.isEmpty()) {
            aucunEnregistrementMessage.visibility = View.VISIBLE
        } else {
            aucunEnregistrementMessage.visibility = View.GONE
        }

        val effacerButton: Button = view.findViewById(R.id.btnEffacer)
        effacerButton.setOnClickListener {
            afficherDialogEffacerModele(listeEnregistrementVoiture)
        }
        return view
    }

    private fun afficherDialogEffacerModele(modelesEnregistres: List<String>) {
        AlertDialog.Builder(requireContext())
            .setTitle("Choisir le modèle à effacer")
            .setItems(modelesEnregistres.toTypedArray()) { _, which ->
                val modeleSelectionne = modelesEnregistres[which]
                val marque = getMarqueDuModele(modeleSelectionne)
                sourceVoitures.effacerModele(marque, modeleSelectionne)

                Toast.makeText(
                    requireContext(),
                    "Le modèle enregistré \"$modeleSelectionne\" a été effacé",
                    Toast.LENGTH_SHORT
                ).show()

                majModelesEnregistres()
            }
            .setNegativeButton("Annuler", null)
            .show()
    }

    private fun majModelesEnregistres() {
        val marqueChoisi = "Toyota"
        val listeEnregistrementVoiture = sourceVoitures.getModelesDeVoiture()[marqueChoisi] ?: emptyList()
        if (listeEnregistrementVoiture.isEmpty()) {
            aucunEnregistrementMessage.visibility = View.VISIBLE
        } else {
            aucunEnregistrementMessage.visibility = View.GONE
        }
    }

    private fun getMarqueDuModele(modeleEfface: String?): String {
        if (modeleEfface != null) {
            for ((marque, modeles) in sourceVoitures.getModelesDeVoiture()) {
                if (modeles.contains(modeleEfface)) {
                    return marque
                }
            }
        }
        return ""
    }

    override fun onModeleClick(modele: String) {
        val message = "Vous avez cliqué sur le modèle : $modele"
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        val action = EnregistrementVoitureFragmentDirections.actionEnregistrementsFragmentToÉcranDétail()
        findNavController().navigate(action)
    }
}
