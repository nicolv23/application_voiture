package com.example.louemonchar.presentation.inscription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.louemonchar.MainActivity
import com.example.louemonchar.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

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



