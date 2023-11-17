package com.example.louemonchar.presentation.connexion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.louemonchar.R


class ConnexionVue : Fragment(), ConnexionInterface.Vue {

    private lateinit var presentateur: ConnexionInterface.Presentateur

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_connexion, container, false)
        presentateur = ConnexionPresentateur(this)
        initComponent(view)
        return view
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
            Navigation.findNavController(requireView()).navigate(R.id.action_connexionVue2_to_marquesAuto)
        }


        leTextVoirGps.setOnClickListener {
            // Appeler l'action de navigation associée au clic du bouton
            Navigation.findNavController(requireView()).navigate(R.id.action_connexionVue2_to_recuperationVue)
        }


    }

    override fun navigationVersInscriptionFragment() {
        // Utiliser Navigation component pour naviguer vers InscriptionFragment
        Navigation.findNavController(requireView()).navigate(R.id.action_connexionVue2_to_inscriptionVue)

    }




/*
    override fun navigationVersMarquesFragment() {
        // Utiliser Navigation component pour naviguer vers InscriptionFragment
        Navigation.findNavController(requireView()).navigate(R.id.Navigation_Vers_MarquesFragment)

    }

 */





}

