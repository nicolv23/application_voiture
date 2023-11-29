package com.example.louemonchar

import BDVoitures
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.marqueauto.ModeleEnregistrement
import com.example.louemonchar.marqueauto.ModeleListener
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon
import com.example.louemonchar.sourceDonnees.SourceVoitures
import com.example.louemonchar.vue.RecyclerViewSurligne

class EnregistrementModele : Fragment(), ModeleEnregistrement.ModeleClickListener, ModeleListener {
    private lateinit var sourceVoitures: SourceVoitures
    private lateinit var adapteur: ModeleEnregistrement
    private lateinit var recyclerView: RecyclerView
    private var marqueAuto: String = ""
    private var modeleEnregistres: MutableList<String> = mutableListOf()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sourceVoitures = SourceDeVoituresBidon(requireContext())
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
                AlertDialog.Builder(requireContext())
                    .setTitle("Confirmer la suppression")
                    .setMessage("Voulez-vous effacer le modèle \"$modeleSelectionne\"?")
                    .setPositiveButton("Oui") { _, _ ->
                        if (marque.isNotEmpty()) {
                            sourceVoitures.effacerModele(marque, modeleSelectionne)
                            Toast.makeText(
                                requireContext(),
                                "Le modèle enregistré \"$modeleSelectionne\" a été effacé",
                                Toast.LENGTH_SHORT
                            ).show()
                            chargerModelesEnregistres(marque)
                        }
                    }
                    .setNegativeButton("Non", null)
                    .show()
            }
            .setNegativeButton("Annuler", null)
            .show()
    }

    override fun getModelesEnregistres(marque: String): List<String> {
        return modeleEnregistres.toList()
    }

    private fun chargerModelesDepuisSQLite() {
        modeleEnregistres.clear()
        val bdVoitures = BDVoitures(requireContext(), SourceDeVoituresBidon(requireContext()))
        val modeleEnregistresSqlite = bdVoitures.lireModelesEnregistres()
        Log.d("ModelesEnregistres", "Modèles enregistrés de la bd: $modeleEnregistresSqlite")
        modeleEnregistres.addAll(modeleEnregistresSqlite)
        majModelesEnregistres()

        // Afficher le message Toast avec les modèles enregistrés pendant 5 secondes
        val toast = Toast.makeText(requireContext(), "Modèles enregistrés : $modeleEnregistresSqlite", Toast.LENGTH_LONG)
        toast.show()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ toast.cancel() }, 5000)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_enregistrement, container, false)
        boutonRetour = view.findViewById(R.id.btnRetour)
        boutonRetour.setOnClickListener { Navigation.findNavController(requireView()).navigate(R.id.action_enregistrementsModele_to_marquesAuto) }
        recyclerView = view.findViewById(R.id.recyclerViewModele)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if (!::adapteur.isInitialized) {
            adapteur = ModeleEnregistrement(requireContext(), modeleEnregistres, this)
            recyclerView.adapter = adapteur
        }

        val surlignerModele = RecyclerViewSurligne(recyclerView)
        recyclerView.addItemDecoration(surlignerModele)
        majModelesEnregistres()

        val effacerButton: Button = view.findViewById(R.id.btnEffacer)
        effacerButton.setOnClickListener {
            afficherDialogEffacerModele(modeleEnregistres)
        }

        // Appeler testerSourceDeVoitures après initialisation
        chargerModelesDepuisSQLite()
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
        val action = EnregistrementModeleDirections.actionEnregistrementsFragmentToÉcranDétail(modele)
        action.modeleSelectionne = modele
        findNavController().navigate(action)
    }
}
