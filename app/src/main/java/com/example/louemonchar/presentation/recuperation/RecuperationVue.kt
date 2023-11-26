package com.example.louemonchar.presentation.recuperation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.louemonchar.R

class RecuperationVue : Fragment(), RecuperationInterface.Vue {

    private lateinit var presentateur: RecuperationInterface.Presentateur
    private lateinit var depart: EditText
    private lateinit var destination: EditText
    private lateinit var bouton: Button
    lateinit var boutonRetour: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recuperation, container, false)
        boutonRetour = view.findViewById(R.id.btnRetour)
        boutonRetour.setOnClickListener { presentateur.allezConnexion() }
        depart = view.findViewById(R.id.depart)
        destination = view.findViewById(R.id.destination)
        bouton = view.findViewById(R.id.lancerGPS)

        presentateur = RecuperationPresentateur(this)

        bouton.setOnClickListener {
            val leDepart = depart.text.toString()
            val laDestination = destination.text.toString()
            presentateur.onClickLancerGPS(leDepart, laDestination)
        }

        return view
    }

    override fun afficherErreur(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun lancerNavigation(depart: String, destination: String) {
        val uri = Uri.parse("https://www.google.com/maps/dir/$depart/$destination")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun connexion(){
        Navigation.findNavController(requireView()).navigate(R.id.action_recuperationVue_to_connexionVue2)
    }
}
