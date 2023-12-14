package com.example.louemonchar

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DateLocationFragment : Fragment() {

    private lateinit var nomLogo: TextView
    private lateinit var imageLogo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_date_location, container, false)

        nomLogo = view.findViewById(R.id.nomVoitures)
        imageLogo = view.findViewById(R.id.logoVoitures)

        // Récupérer les arguments
        val name = requireArguments().getString("name")
        val image = requireArguments().getInt("image")

        // Utilisez name et image comme nécessaire
        nomLogo.text = name
        imageLogo.setImageResource(image)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leBoutonApprouver = view.findViewById<Button>(R.id.bouton_approuver)

        leBoutonApprouver.setOnClickListener {



            // Appeler l'action de navigation associée au clic du bouton
            view.findNavController().navigate(R.id.vers_voituresDisponiblesFragment)
        }
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
