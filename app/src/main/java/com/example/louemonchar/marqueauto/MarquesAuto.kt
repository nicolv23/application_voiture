package com.example.louemonchar.marqueauto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.louemonchar.R
import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var bottomNavigationView: BottomNavigationView

class MarquesAuto : Fragment(), IContratVueMarque.Vue {


    var menuBtn: ImageButton? = null
    lateinit var boutonDeconnexion: Button




    private val presentateur: IContratVueMarque.Presentateur by lazy {
        MarquePresentateur(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_marques_auto, container, false)

        val btnAllerEnregistrer: Button = view.findViewById(R.id.btnAllerEnregistrer)




        // Récupérer le GridLayout
        val gridLayout: GridLayout = view.findViewById(R.id.gridLayout)

        // Itérer sur les enfants du GridLayout
        for (i in 0 until gridLayout.childCount) {
            val child: View = gridLayout.getChildAt(i)
            if (child is ImageView) {
                // Ajouter le setOnClickListener pour chaque ImageView
                child.setOnClickListener {
                    val marque = when (child.id) {
                        R.id.toyota -> "Toyota"
                        R.id.honda -> "Honda"
                        R.id.bmw -> "BMW"
                        R.id.porsche -> "Porsche"
                        R.id.mazda -> "Mazda"
                        R.id.hyundai -> "Hyundai"
                        R.id.tesla -> "Tesla"
                        R.id.lexus -> "Lexus"
                        R.id.mercedes -> "Mercedes"
                        R.id.jeep -> "Jeep"
                        R.id.volvo -> "Volvo"
                        R.id.nissan -> "Nissan"
                        // Ajoutez d'autres cas pour les autres marques
                        else -> "Cette marque n'est pas dans la liste"
                    }

                    presentateur.onMarqueSelected(marque)
                }
            }
        }

        boutonDeconnexion = view.findViewById(R.id.button1)
        boutonDeconnexion.setOnClickListener { presentateur.deconnexion() }
        presentateur.setToolbarTitle()

        btnAllerEnregistrer.setOnClickListener(){
        allerVersEnregistrerUneVoiture()
        }

        return view

    }

    override fun afficherModeleVoitures(marque: String) {
        val action =
            com.example.louemonchar.marqueauto.MarquesAutoDirections.actionMarquesAutoToListeVoitures(
                marque
            )
        findNavController().navigate(action)
    }

    override fun allezVersConnexion(){
        Navigation.findNavController(requireView()).navigate(R.id.action_marquesAuto_to_connexionVue2)
    }

    override fun allerVersEnregistrerUneVoiture() {
        val ecranEnregistrer = MarquesAutoDirections.actionMarquesAutoToEnregistrerVoitureFragment()
        findNavController().navigate(ecranEnregistrer)
    }

    override fun setToolbarTitle(titre: String) {
        requireActivity().title = titre
    }
}


