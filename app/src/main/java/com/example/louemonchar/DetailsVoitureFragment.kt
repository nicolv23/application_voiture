
package com.example.louemonchar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Déclaration de la classe DetailsVoitureFragment qui étend Fragment
class DetailsVoitureFragment : Fragment() {

    private lateinit var boutonReserver: Button

    // Déclaration des propriétés pour les éléments de la vue
    private lateinit var marqueModèle: TextView
    private lateinit var année: TextView
    private lateinit var nbrPassagers: TextView
    private lateinit var propriétaire: TextView
    private lateinit var dateLocation: TextView
    private lateinit var imageVoiture: ImageView

    // Méthode appelée lors de la création de la vue du fragment
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Gonfler le layout pour ce fragment
        val view = inflater.inflate(R.layout.fragment_details_voiture, container, false)

        // Initialisation des propriétés avec les vues du layout
        marqueModèle = view.findViewById(R.id.marque_modèle_details_voiture)
        année = view.findViewById(R.id.annee_details_voiture)
        nbrPassagers = view.findViewById(R.id.nombre_details_passager)
        propriétaire = view.findViewById(R.id.details_nom_propriétaire)
        dateLocation = view.findViewById(R.id.details_date_location)
        imageVoiture = view.findViewById(R.id.img_details_voiture)

        // Récupérer les arguments passés au fragment
        val arguments = requireArguments()
        val marqueModèleArg = arguments.getString("marque_modèle_details_voiture")
        val annéeArg = arguments.getInt("annee_details_voiture")
        val passagersArg = arguments.getString("nombre_details_passager")
        val proprietaireArg = arguments.getString("details_nom_propriétaire")
        val dateLocationArg = arguments.getString("details_date_location")
        val imageResArg = arguments.getString("img_details_voiture")

        // Utiliser les arguments pour mettre à jour les vues du fragment DetailsVoitureFragment
        marqueModèle.text = marqueModèleArg
        année.text = annéeArg.toString()
        nbrPassagers.text = passagersArg
        propriétaire.text = proprietaireArg
        dateLocation.text = dateLocationArg
        Glide.with(view.context).load(imageResArg).into(imageVoiture)



        //Fontion sur le bouton pour passer les infos vers réserver
        boutonReserver = view.findViewById(R.id.détails)
        boutonReserver.setOnClickListener {
            val bundle = Bundle().apply {
                putString("modèle_voitures_reservees", marqueModèle.text.toString())
                putString("année_voitures_reservees", année.text.toString())
                putString("passagers_voitures_reservees", nbrPassagers.text.toString())
                putString("propriétaire_voitures_reservees", propriétaire.text.toString())
                putString("date_de_location_reservees", dateLocation.text.toString())
                // Remplacer 'R.drawable.default_image' par votre ressource d'image par défaut
                putString("img_voitures_reservees", imageResArg)
            }

            view.findNavController().navigate(R.id.vers_reserverVoitureFragment, bundle)
        }



        return view
    }






}


