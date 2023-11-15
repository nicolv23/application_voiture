package com.example.louemonchar

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.modele.ModeleEnregistrement
import com.example.louemonchar.modele.ModeleListener
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon
import com.example.louemonchar.sourceDonnees.SourceVoitures

class EnregistrementModele : Fragment(), ModeleEnregistrement.ModeleClickListener, ModeleListener {
    private lateinit var sourceVoitures: SourceVoitures
    private lateinit var adapteur: ModeleEnregistrement
    private lateinit var recyclerView: RecyclerView
    private var marqueAuto: String = ""
    private var modeleEnregistres: MutableList<String> = mutableListOf()
    private lateinit var modeleListener: ModeleListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ModeleListener) {
            modeleListener = context
        } else {
            throw RuntimeException("$context doit implémenter ModeleListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sourceVoitures = SourceDeVoituresBidon()
    }

    private fun chargerModelesEnregistres(marqueAuto: String) {
        modeleEnregistres.clear()
        modeleEnregistres.addAll(sourceVoitures.getModelesEnregistres()[marqueAuto] ?: emptyList())
        majModelesEnregistres()
    }

    private fun majModelesEnregistres() {
        if (!::adapteur.isInitialized) {
            adapteur = ModeleEnregistrement(requireContext(), modeleEnregistres, this)
            recyclerView.adapter = adapteur
        } else {
            adapteur.updateModelesEnregistres(modeleEnregistres)
            adapteur.notifyDataSetChanged()
        }
    }

    private fun afficherDialogEffacerModele(modelesEnregistres: List<String>) {
        AlertDialog.Builder(requireContext())
            .setTitle("Choisir le modèle à effacer")
            .setItems(modelesEnregistres.toTypedArray()) { _, which ->
                val modeleSelectionne = modelesEnregistres[which]
                val marque = getMarqueDuModele(modeleSelectionne)
                sourceVoitures.effacerModele(marque, modeleSelectionne)


                sourceVoitures.getModelesEnregistres()
                Toast.makeText(
                    requireContext(),
                    "Le modèle enregistré \"$modeleSelectionne\" a été effacé",
                    Toast.LENGTH_SHORT
                ).show()
                chargerModelesEnregistres(marqueAuto)
            }
            .setNegativeButton("Annuler", null)
            .show()
    }

    override fun getModelesEnregistres(marque: String): List<String> {
        return modeleEnregistres.toList()
    }

    private fun testerSourceDeVoitures() {
        sourceVoitures.enregistrerModele("Toyota", "Magog")
        modeleEnregistres = sourceVoitures.getModelesEnregistres()[marqueAuto]?.toMutableList() ?: mutableListOf()
        chargerModelesEnregistres("Toyota") // Met à jour les modèles enregistrés pour "Toyota"
        Log.d("EnregistrementModele", "Modèles chargés : $modeleEnregistres")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_enregistrement, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewModele)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapteur = ModeleEnregistrement(requireContext(), modeleEnregistres, this)
        recyclerView.adapter = adapteur
        majModelesEnregistres()

        val effacerButton: Button = view.findViewById(R.id.btnEffacer)
        effacerButton.setOnClickListener {
            afficherDialogEffacerModele(modeleEnregistres)
        }

        // Appeler testerSourceDeVoitures après initialisation
        testerSourceDeVoitures()
        return view
    }

    private fun getMarqueDuModele(modeleEfface: String?): String {
        if (modeleEfface != null) {
            for ((marque, modeles) in sourceVoitures.getModelesEnregistres()) {
                if (modeles.contains(modeleEfface)) {
                    return marque
                }
            }
        }
        return ""
    }

    override fun onModeleEnregistre(marque: String, modele: String) {
        chargerModelesEnregistres(marque)
    }

    override fun onModeleClick(modele: String) {
        val action = EnregistrementModeleDirections.actionEnregistrementsFragmentToÉcranDétail()
        findNavController().navigate(action)
    }
}
