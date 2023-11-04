package com.example.louemonchar.vue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.louemonchar.interfaces.IContratVueAccueil
import com.example.louemonchar.R
import com.example.louemonchar.présentateur.AccueilPresentateur

class AccueilFragment : Fragment(), IContratVueAccueil.Vue {
    private lateinit var presentateur: IContratVueAccueil.Presentateur

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_accueil, container, false)
        val bouton = view.findViewById<Button>(R.id.bienvenue)

        presentateur = AccueilPresentateur(this)
        bouton.setOnClickListener{
            presentateur.onButtonClicked()
            findNavController().navigate(R.id.action_accueilFragment_to_marquesAuto)
        }
        return view
    }

    override fun setToolbarTitle(title: String) {
        // Changer le titre de la barre d'outils ou de toute autre vue appropriée
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }

    override fun getString(resourceId: String): String {
        return resources.getString(resources.getIdentifier(resourceId, "string", requireActivity().packageName))
    }


    override fun onDestroy() {
        presentateur.onDestroy()
        super.onDestroy()
    }
}
