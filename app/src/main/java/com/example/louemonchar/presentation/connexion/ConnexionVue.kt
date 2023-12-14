package com.example.louemonchar.presentation.connexion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.louemonchar.R
import android.view.MenuInflater
import com.example.louemonchar.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ConnexionVue : Fragment(), ConnexionInterface.Vue {

    private lateinit var presentateur: ConnexionInterface.Présentateur

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_connexion, container, false)
        presentateur = ConnexionPrésentateur(this)
        initComponent(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("ConnexionVue", "onViewCreated called")

        val leBoutonconnexion = view.findViewById<Button>(R.id.boutonconnexion)
        leBoutonconnexion.setOnClickListener {
            Log.d("ConnexionVue", "Boutonconnexion clicked")

            try {
                // Appeler l'action de navigation associée au clic du bouton
                Navigation.findNavController(requireView()).navigate(R.id.vers_accueilFragment)
            } catch (e: Exception) {
                Log.e("ConnexionVue", "Exception while navigating", e)
            }
        }
    }






    private fun initComponent(view: View) {
        val leTextCréerUnCompte = view.findViewById<View>(R.id.textCréerUnCompte)
        val leTextVoirGps = view.findViewById<View>(R.id.voirGPS)
        val leBoutonconnexion = view.findViewById<View>(R.id.boutonconnexion)

        leTextCréerUnCompte.setOnClickListener {
            // Appeler la fonction pour changer de fragment
            presentateur.tenterConnexion()
        }


        leBoutonconnexion.setOnClickListener {
            // Appeler l'action de navigation associée au clic du bouton
            Navigation.findNavController(requireView()).navigate(R.id.vers_accueilFragment)
        }


        leTextVoirGps.setOnClickListener {
            // Appeler l'action de navigation associée au clic du bouton
            //Navigation.findNavController(requireView()).navigate(R.id.action_connexionVue2_to_recuperationVue)
        }


    }

    override fun navigationVersInscriptionFragment() {
        // Utiliser Navigation component pour naviguer vers InscriptionFragment
        Navigation.findNavController(requireView()).navigate(R.id.vers_inscriptionVue)

    }







    /*
        override fun navigationVersMarquesFragment() {
            // Utiliser Navigation component pour naviguer vers InscriptionFragment
            Navigation.findNavController(requireView()).navigate(R.id.Navigation_Vers_MarquesFragment)

        }

     */





    override fun onResume() {
        super.onResume()

        // Masquer le menu de navigation dans ce fragment
        (activity as? MainActivity)?.apply {
            hideBottomNavigation()

            val fab = requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButton)
            hideFloatingActionButton(fab)
        }
    }

    override fun onPause() {
        super.onPause()

        // Réafficher le menu de navigation en quittant ce fragment
        (activity as? MainActivity)?.apply {
            showBottomNavigation()

            val fab = requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButton)
            showFloatingActionButton(fab)
        }
    }


}







