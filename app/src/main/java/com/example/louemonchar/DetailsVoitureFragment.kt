/*
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


    private lateinit var localiserTextView: TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

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

 */

package com.example.louemonchar

import BDVoitures
import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.louemonchar.http.Auto
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task


class DetailsVoitureFragment : Fragment() {
    private lateinit var transmission :TextView
    private lateinit var modelVoiture :TextView
    private lateinit var boutonReserver: Button
    private lateinit var marqueModèle: TextView
    private lateinit var année: TextView
    private lateinit var nbrPassagers: TextView
    private lateinit var propriétaire: TextView
    private lateinit var dateLocation: TextView
    private lateinit var imageVoiture: ImageView
    private lateinit var localiserTextView: TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var btnFavoris : ImageButton
    private lateinit var code : TextView
    private lateinit var prix :TextView
    private lateinit var etat :TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details_voiture, container, false)

        btnFavoris = view.findViewById(R.id.ajouterFavoris)
        boutonReserver = view.findViewById(R.id.détails)
        marqueModèle = view.findViewById(R.id.marque_modèle_details_voiture)
        modelVoiture = view.findViewById(R.id.modèle_details_voiture)
        année = view.findViewById(R.id.annee_details_voiture)
        nbrPassagers = view.findViewById(R.id.nombre_details_passager)
        propriétaire = view.findViewById(R.id.details_nom_propriétaire)
        dateLocation = view.findViewById(R.id.details_date_location)
        imageVoiture = view.findViewById(R.id.img_details_voiture)
        localiserTextView = view.findViewById(R.id.localiser)
        transmission = view.findViewById(R.id.transmission_details_voiture)
        code = view.findViewById(R.id.code_details_voiture)
        prix = view.findViewById(R.id.details_prix)
        etat = view.findViewById(R.id.details_état)

        // Récupération des données passées au fragment
        val arguments = requireArguments()
        code.text = arguments.getString("code_details_voiture")
        modelVoiture.text = arguments.getString("code_details_voiture")
        marqueModèle.text = arguments.getString("marque_modèle_details_voiture")
        transmission.text = arguments.getString("transmission_details_voiture")
        année.text = arguments.getInt("annee_details_voiture").toString()
        nbrPassagers.text = arguments.getString("nombre_details_passager")
        propriétaire.text = arguments.getString("details_nom_propriétaire")
        dateLocation.text = arguments.getString("details_date_location")
        prix.text = arguments.getInt("details_prix").toString()
        etat.text = arguments.getString("details_état")
        Glide.with(view.context).load(arguments.getString("img_details_voiture")).into(imageVoiture)

        btnFavoris.setOnClickListener{

            val voitureFavoris  = Auto(

                code = code.text.toString(),
                code_propriétaire = propriétaire.text.toString(),
                marque = marqueModèle.text.toString(),
                transmission = transmission.text.toString(),
                modèle = modelVoiture.text.toString(),
                année = année.text.toString().toInt(),
                image = imageVoiture.toString(),
                etat = etat.text.toString(),
                prix = prix.text.toString().toInt(),
                location = dateLocation.text.toString()


            )

           val dbVoiture = BDVoitures(requireContext())
            dbVoiture.insererVoiture(voitureFavoris)
        }

        boutonReserver.setOnClickListener {
            // Logique pour la réservation
            val bundle = Bundle().apply {
                putString("modèle_voitures_reservees", marqueModèle.text.toString())
                putString("année_voitures_reservees", année.text.toString())
                putString("passagers_voitures_reservees", nbrPassagers.text.toString())
                putString("propriétaire_voitures_reservees", propriétaire.text.toString())
                putString("date_de_location_reservees", dateLocation.text.toString())
                putString("img_voitures_reservees", arguments.getString("img_details_voiture"))
            }

            view.findNavController().navigate(R.id.vers_reserverVoitureFragment, bundle)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        localiserTextView.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            } else {
                obtenirPositionEtAfficherItinéraire()
            }
        }

        return view
    }

    @SuppressLint("MissingPermission")
    private fun obtenirPositionEtAfficherItinéraire() {
        val locationTask: Task<Location> = fusedLocationClient.lastLocation
        locationTask.addOnSuccessListener { location: Location? ->
            if (location != null) {
                // Coordonnées de la destination
                val destinationLat = 45.60008
                val destinationLng = -73.63251

                // Construction de l'URI pour Google Maps
                val uri = Uri.parse("http://maps.google.com/maps?saddr=${location.latitude},${location.longitude}&daddr=$destinationLat,$destinationLng")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)
            } else {
                // Gérer le cas où la localisation est null
                Log.e("DetailsVoitureFragment", "Localisation non disponible")
            }
        }
    }



    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }
}






