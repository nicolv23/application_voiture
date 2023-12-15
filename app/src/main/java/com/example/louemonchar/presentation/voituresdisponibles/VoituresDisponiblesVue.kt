package com.example.louemonchar.presentation.voituresdisponibles

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.louemonchar.R
import com.example.louemonchar.VoitureAdapter
import com.example.louemonchar.databinding.FragmentVoituresDisponiblesBinding
import com.example.louemonchar.modèle.VoitureUiModèle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale

class VoituresDisponiblesVue : Fragment(), VoituresDisponiblesInterface.View,
    VoitureAdapter.OnItemClickListener {

    private lateinit var binding: FragmentVoituresDisponiblesBinding
    private lateinit var presenter: VoituresDisponiblesInterface.Presenter
    private var voitureAdapter = VoitureAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVoituresDisponiblesBinding.inflate(inflater, container, false)
        presenter = VoituresDisponiblesPresentateur(this)

        setupRecyclerView()
        setupListeners()

        presenter.chargerVoitures()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = voitureAdapter
    }

    private fun setupListeners() {
        binding.boutonDate.setOnClickListener {
            showDatePickerDialog { selectedDate ->
                presenter.setDateLocation(selectedDate)
                presenter.searchByDateRange()
            }
        }

        binding.rechercheModLe.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                presenter.rechercherParModèle(query)
            }
        })

        binding.rechercheModLe.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val query = binding.rechercheModLe.text.toString()
                presenter.rechercherParModèle(query)
                true
            } else {
                false
            }
        }
    }

    override fun afficherVoitures(voitures: List<VoitureUiModèle>) {
        voitureAdapter.setItems(voitures)
        voitureAdapter.notifyDataSetChanged()

        // Vérifier si la liste de voitures n'est pas vide pour cacher la barre de progression
        if (voitures.isNotEmpty()) {
            cacherBarreChargement()
        }
    }

    override fun afficherErreur(message: String) {
        Snackbar.make(binding.root, "Erreur dans la liste des voitures, veuillez réesayer", Snackbar.LENGTH_LONG).show()
    }

    fun naviguerVersDétails(voiture: VoitureUiModèle) {
        val bundle = Bundle().apply {
            putString("marque_modèle_details_voiture", voiture.modèle)
            putSerializable("annee_details_voiture", voiture.année)
            putString("nombre_details_passager", voiture.passagers)
            putString("details_nom_propriétaire", voiture.propriétaire)
            putSerializable("details_date_location", voiture.location.time) // Date en millisecondes
            putInt("img_details_voiture", voiture.imageRes)
        }
        findNavController().navigate(R.id.vers_detailsVoitureFragment, bundle)
    }

    override fun onItemClick(voiture: VoitureUiModèle) {
        montrerBarreChargement()

        lifecycleScope.launch {
            // Simulation de chargement pendant 2 secondes
            delay(2000)
            naviguerVersDétails(voiture)
            cacherBarreChargement()
            afficherMessageChargementTermine()
        }
    }

    override fun montrerBarreChargement() {
        binding.barreProgression.visibility = View.VISIBLE
    }

    override fun cacherBarreChargement() {
        binding.barreProgression.visibility = View.GONE
    }

    private fun afficherMessageChargementTermine() {
        Snackbar.make(binding.root, "Chargement du véhicule terminé", Snackbar.LENGTH_SHORT).show()
    }

    private fun showDatePickerDialog(onDateSelected: (Date) -> Unit) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                val date = format.parse("$year-${monthOfYear + 1}-$dayOfMonth")
                date?.let { onDateSelected(it) }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}
