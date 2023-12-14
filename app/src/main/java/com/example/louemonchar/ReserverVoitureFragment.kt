package com.example.louemonchar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReserverVoitureFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReserverVoitureFragment : Fragment() {
    // Déclaration des propriétés pour les éléments de la vue
    private lateinit var marqueModèle: TextView
    private lateinit var année: TextView
    private lateinit var nbrPassagers: TextView
    private lateinit var propriétaire: TextView
    private lateinit var dateLocation: TextView
    private lateinit var imageVoiture: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Gonfler le layout pour ce fragment
        val view = inflater.inflate(R.layout.fragment_details_voiture, container, false)

        // Initialisation des propriétés avec les vues du layout
        marqueModèle = view.findViewById(R.id.modèle_voitures_reservees)
        année = view.findViewById(R.id.année_voitures_reservees)
        nbrPassagers = view.findViewById(R.id.passagers_voitures_reservees)
        propriétaire = view.findViewById(R.id.propriétaire_voitures_reservees)
        dateLocation = view.findViewById(R.id.date_de_location_reservees)
        imageVoiture = view.findViewById(R.id.img_voitures_reservees)

        // Récupérer les arguments passés au fragment
        val arguments = requireArguments()
        val marqueModèleArg = arguments.getString("modèle_voitures_reservees")
        val annéeArg = arguments.getLong("année_voitures_reservees")
        val passagersArg = arguments.getString("passagers_voitures_reservees")
        val proprietaireArg = arguments.getString("propriétaire_voitures_reservees")
        val dateLocationArg = arguments.getLong("date_de_location_reservees")
        val imageResArg = arguments.getInt("img_voitures_reservees")

        // Utiliser les arguments pour mettre à jour les vues du fragment
        marqueModèle.text = marqueModèleArg
        année.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(annéeArg))
        nbrPassagers.text = passagersArg
        propriétaire.text = proprietaireArg
        dateLocation.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(dateLocationArg))
        imageVoiture.setImageResource(imageResArg)

        return view
    }
}