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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.louemonchar.R
import com.example.louemonchar.VoitureAdapter
import com.example.louemonchar.databinding.FragmentVoituresDisponiblesBinding
import com.example.louemonchar.modèle.VoitureUiModèle
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


    //code temporaire
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ... configuration initiale ...

        // Récupérer le nom du modèle de voiture passé
        val nomModèle = arguments?.getString("nomModèle")
        presenter.chargerVoituresParModèle(nomModèle)
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
    }

    override fun afficherVoitures(voitures: List<VoitureUiModèle>) {
        voitureAdapter.setItems(voitures)
        voitureAdapter.notifyDataSetChanged()
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
        naviguerVersDétails(voiture)
    }

    private fun showDatePickerDialog(onDateSelected: (java.util.Date) -> Unit) {
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

    override fun afficherErreur(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
