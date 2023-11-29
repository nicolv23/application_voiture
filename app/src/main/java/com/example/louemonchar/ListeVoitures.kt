package com.example.louemonchar

import BDVoitures
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.marqueauto.ModeleListener
import com.example.louemonchar.marqueauto.ModeleVoiture
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon
import com.example.louemonchar.sourceDonnees.SourceVoitures
import com.google.android.material.snackbar.Snackbar

class ListeVoitures : Fragment(), ModeleVoiture.ModeleClickListener {

    private lateinit var sourceVoitures: SourceVoitures
    private lateinit var sauvegardeEntreFragments: SharedPreferences
    private var marqueAuto: String? = null
    private var modeleEnregistres: Array<String> = emptyArray()
    private lateinit var modeleListener: ModeleListener
    lateinit var boutonRetour: Button

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
        setHasOptionsMenu(true)
        sourceVoitures = SourceDeVoituresBidon(requireContext())
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
        var boutonRetour = view.findViewById<View>(R.id.btnRetour)
        boutonRetour.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(R.id.action_listeVoitures_to_marquesAuto)

        }
        val modelesVoiture: Map<String, List<String>> = sourceVoitures.getModelesDeVoiture()
        val modeles = modelesVoiture[marqueAuto]

        val boutonEnregistres: Button = view.findViewById(R.id.boutonEnregistres)
        boutonEnregistres.setOnClickListener {
            val action = ListeVoituresDirections.actionListeVoituresToEnregistrementsModele().apply {
                marqueAuto = this@ListeVoitures.marqueAuto ?: ""
                modeleEnregistres = sourceVoitures.getModelesEnregistres()[marqueAuto]?.toTypedArray() ?: emptyArray()
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
        val modelesEnregistres = sourceVoitures.getModelesEnregistres()[marque]?.toMutableList() ?: mutableListOf()

        val bdVoitures = BDVoitures(requireContext(), SourceDeVoituresBidon(requireContext()))

        if (!modelesEnregistres.contains(modele) && !bdVoitures.modeleEstEnregistre(marque, modele)) {
            sourceVoitures.enregistrerModele(marque, modele)
            modelesEnregistres.add(modele)

            bdVoitures.enregistrerModele(marque, modele)

            chargerModelesEnregistres(marque)
            val parentFragment = parentFragment as? ModeleListener
            parentFragment?.onModeleEnregistre(marque, modele)
            modeleListener?.onModeleEnregistre(marque, modele)

            view?.let { Snackbar.make(it, "Le modèle: $modele a été enregistré", Snackbar.LENGTH_SHORT).show() }
        } else {
            view?.let { Snackbar.make(it, "Le modèle: $modele est déjà enregistré", Snackbar.LENGTH_SHORT).show() }
        }
    }





    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflater le menu spécifique au fragment; cela ajoutera des éléments à la barre d'action s'il est présent.
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Gérer les clics d'éléments du menu ici.
        return when (item.itemId) {
            R.id.action_settings -> {
                // Faire quelque chose quand l'élément de menu "action_settings" est sélectionné.
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
