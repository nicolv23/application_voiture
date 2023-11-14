package com.example.louemonchar

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.modele.ModeleListener
import com.example.louemonchar.modele.ModeleVoiture
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon
import com.example.louemonchar.sourceDonnees.SourceVoitures
import com.google.android.material.snackbar.Snackbar

class ListeVoitures : Fragment(), ModeleVoiture.ModeleClickListener {

    private lateinit var sourceVoitures: SourceVoitures
    private lateinit var sauvegardeEntreFragments: SharedPreferences
    private var marqueAuto: String? = null
    private var modeleEnregistres: Array<String> = emptyArray()
    private lateinit var modeleListener: ModeleListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ModeleListener) {
            modeleListener = context
        } else {
            throw RuntimeException("$context doit implémenter ModeleListener")
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (parentFragment is ModeleListener) {
            modeleListener = parentFragment as ModeleListener
        } else if (activity is ModeleListener) {
            modeleListener = activity as ModeleListener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sourceVoitures = SourceDeVoituresBidon()
        sauvegardeEntreFragments = requireActivity().getPreferences(Context.MODE_PRIVATE)
        if (parentFragment is ModeleListener) {
            modeleListener = parentFragment as ModeleListener
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_liste_voitures, container, false)
        marqueAuto = requireArguments().getString("marqueAuto")

        if (marqueAuto != null) {
            chargerModelesEnregistres(marqueAuto!!)
            val marqueTextView: TextView = view.findViewById(R.id.marqueTextView)
            marqueTextView.text = "Marque de voiture : $marqueAuto"
        } else {
            Snackbar.make(view, "Aucun modèle sélectionné", Snackbar.LENGTH_SHORT).show()
            return view
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        val modelesVoiture: Map<String, List<String>> = sourceVoitures.getModelesDeVoiture()
        val modeles = modelesVoiture[marqueAuto]

        val boutonEnregistres: Button = view.findViewById(R.id.boutonEnregistres)
        boutonEnregistres.setOnClickListener {
            val action = ListeVoituresDirections.actionListeVoituresToEnregistrementsModele().apply {
                this@ListeVoitures.marqueAuto = this@ListeVoitures.marqueAuto ?: ""
                this@ListeVoitures.modeleEnregistres = sourceVoitures.getModelesEnregistres()[this@ListeVoitures.marqueAuto]?.toTypedArray() ?: emptyArray()
            }
            findNavController().navigate(action)
        }

        if (!modeles.isNullOrEmpty()) {
            recyclerView.visibility = View.VISIBLE
            recyclerView.layoutManager =
                GridLayoutManager(requireContext(), 6, GridLayoutManager.HORIZONTAL, false)
            val adapter = ModeleVoiture(modeles, this)
            recyclerView.adapter = adapter
        } else {
            recyclerView.visibility = View.GONE
        }

        return view
    }

    private fun chargerModelesEnregistres(marqueAuto: String) {
        val listeModeles = emptySet<String>()
        modeleEnregistres = sauvegardeEntreFragments.getStringSet(marqueAuto, listeModeles)?.toTypedArray()
            ?: emptyArray()
    }

    override fun onModeleClick(modele: String) {
        val marque = marqueAuto ?: ""
        sourceVoitures.enregistrerModele(marque, modele)
        chargerModelesEnregistres(marque)

        val modelesEnregistres = sourceVoitures.getModelesEnregistres()[marque]?.toTypedArray() ?: emptyArray()
        val modeleEstEnregistre = modelesEnregistres.contains(modele)

        if (modeleEstEnregistre) {
            modeleListener?.onModeleEnregistre(marque, modele)
            view?.let { Snackbar.make(it, "Le modèle: $modele a été enregistré", Snackbar.LENGTH_SHORT).show() }
        } else {
            view?.let { Snackbar.make(it, "Erreur lors de l'enregistrement du modèle: $modele", Snackbar.LENGTH_SHORT).show() }
        }
    }

}
