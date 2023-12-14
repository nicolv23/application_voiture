
package com.example.louemonchar

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
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
        val annéeArg = arguments.getSerializable("annee_details_voiture") as? Date
        val passagersArg = arguments.getString("nombre_details_passager")
        val proprietaireArg = arguments.getString("details_nom_propriétaire")
        val dateLocationArg = arguments.getSerializable("details_date_location") as? Date
        val imageResArg = arguments.getInt("img_details_voiture")

        // Utiliser les arguments pour mettre à jour les vues du fragment DetailsVoitureFragment
        marqueModèle.text = marqueModèleArg
        année.text = annéeArg?.let { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it) } ?: ""
        nbrPassagers.text = passagersArg
        propriétaire.text = proprietaireArg
        dateLocation.text = dateLocationArg?.let { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it) } ?: ""
        imageVoiture.setImageResource(imageResArg)




        //Poure passer les informations à réservée
        boutonReserver = view.findViewById(R.id.détails) // Assurez-vous que l'ID est correct

        // Ajoutez un OnClickListener au bouton
        boutonReserver.setOnClickListener {
            // Récupérer les informations à transmettre
            val marqueModele = marqueModèle.text.toString()
            val annee = année.text.toString()
            val passagers = nbrPassagers.text.toString()
            val proprietaire = propriétaire.text.toString()
            val dateLocation = dateLocation.text.toString()
            val imageRes = R.id.img_voitures_reservees/* Obtenez l'ID de la ressource de l'image, si nécessaire */

            // Créer un objet Bundle pour transmettre les informations au fragment suivant
            val bundle = Bundle()
            bundle.putString("modèle_voitures_reservees", marqueModele)
            bundle.putString("année_voitures_reservees", annee)
            bundle.putString("passagers_voitures_reservees", passagers)
            bundle.putString("propriétaire_voitures_reservees", proprietaire)
            bundle.putString("date_de_location_reservees", dateLocation)
            bundle.putInt("img_voitures_reservees", imageRes)

            // Naviguer vers le fragment VoituresReseveesFragment avec les arguments
            view.findNavController().navigate(R.id.vers_reserverVoitureFragment, bundle)
        }



        return view
    }






}


