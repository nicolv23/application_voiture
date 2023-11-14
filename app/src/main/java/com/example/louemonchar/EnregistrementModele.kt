package com.example.louemonchar

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.modele.ModeleListener
import com.example.louemonchar.modele.ModeleVoiture
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon
import com.example.louemonchar.sourceDonnees.SourceVoitures
import com.google.android.material.snackbar.Snackbar

class EnregistrementModele : Fragment(), ModeleVoiture.ModeleClickListener, ModeleListener {
    private lateinit var sourceVoitures: SourceVoitures
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ModeleVoiture
    private lateinit var sauvegardeEntreFragments: SharedPreferences
    private var marqueAuto: String = ""
    private var modeleEnregistres: List<String> = emptyList()
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
        sauvegardeEntreFragments = requireActivity().getPreferences(Context.MODE_PRIVATE)
    }

    override fun onModeleEnregistre(marque: String, modele: String) {
        sourceVoitures.enregistrerModele(marque, modele)
        majModelesEnregistres()
        sauvegarderModelesEnregistres(marque)
        Snackbar.make(requireView(), "Modèle enregistré : $modele", Snackbar.LENGTH_SHORT).show()
        val action = EnregistrementModeleDirections.actionEnregistrementsFragmentToÉcranDétail()
        findNavController().navigate(action)
    }

    override fun getModelesEnregistres(marque: String): List<String> {
        return modeleEnregistres.toList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_enregistrement, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewModele)
        val args = requireArguments()
        marqueAuto = args.getString("marqueAuto") ?: ""
        chargerModelesEnregistres(marqueAuto)

        adapter = ModeleVoiture(modeleEnregistres, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val effacerButton: Button = view.findViewById(R.id.btnEffacer)
        effacerButton.setOnClickListener {
            afficherDialogEffacerModele(modeleEnregistres)
        }
        return view
    }



    private fun sauvegarderModelesEnregistres(marque: String) {
        val listeEnregistrementVoiture = getModelesEnregistres(marque)
        val modificationModele = sauvegardeEntreFragments.edit()
        modificationModele.putStringSet(marque, listeEnregistrementVoiture.toSet())
        modificationModele.apply()
    }

    private fun chargerModelesEnregistres(marqueAuto: String) {
        modeleEnregistres = sauvegardeEntreFragments.getStringSet(marqueAuto, emptySet())?.toList() ?: emptyList()
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
        val marqueChoisi = marqueAuto ?: ""
        val listeEnregistrementVoiture = getModelesEnregistres(marqueChoisi)
        adapter.majModeles(listeEnregistrementVoiture)
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

    override fun onDestroyView() {
        sauvegarderModelesEnregistres(requireArguments().getString("marqueAuto") ?: "")
        super.onDestroyView()
    }

    override fun onModeleClick(modele: String) {
        onModeleEnregistre(requireArguments().getString("marqueAuto")!!, modele)
    }
}
