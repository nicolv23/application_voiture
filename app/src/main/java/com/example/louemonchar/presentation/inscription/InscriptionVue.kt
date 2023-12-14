package com.example.louemonchar.presentation.inscription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.louemonchar.R
import androidx.navigation.Navigation
import com.example.louemonchar.presentation.connexion.ConnexionInterface
import com.example.louemonchar.presentation.connexion.ConnexionPrésentateur

class InscriptionVue : Fragment(), InscriptionInterface.Vue {

    private lateinit var presentateur: InscriptionInterface.Présentateur

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inscription, container, false)
        presentateur = InscriptionPrésentateur(this)
        initComponent(view)
        return view
    }

    private fun initComponent(view: View) {
        val leBoutonenregistrer = view.findViewById<View>(R.id.boutonenregistrer)


        leBoutonenregistrer.setOnClickListener {
            // Appeler la fonction pour changer de fragment
            presentateur.tenterInscription()
        }
    }

    override fun navigationVersConnexionFragment() {
        // Utiliser Navigation component pour naviguer vers InscriptionFragment
        Navigation.findNavController(requireView()).navigate(R.id.vers_connexionVue)

    }

}



