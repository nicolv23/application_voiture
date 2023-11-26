package com.example.louemonchar.vue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.louemonchar.R

class VuePaiement : Fragment() {
    lateinit var boutonRetour: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.paiement, container, false)
        boutonRetour = vue.findViewById(R.id.btnRetour)
        boutonRetour.setOnClickListener { Navigation.findNavController(requireView()).navigate(R.id.action_vuePaiement_to_marquesAuto ) }

        return vue
    }

}