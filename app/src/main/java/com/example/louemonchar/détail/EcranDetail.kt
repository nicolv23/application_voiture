package com.example.louemonchar.détail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.louemonchar.R

class EcranDetail : Fragment() {

    lateinit var navController: NavController
    lateinit var textview1: TextView
    lateinit var textview2: TextView
    lateinit var textview3: TextView
    lateinit var textview4: TextView
    lateinit var textview5: TextView
    lateinit var textview6: TextView
    lateinit var image: ImageView
    lateinit var button: Button
    lateinit var contact: Button
    var presentateur = DetailPresentateur(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.detail_auto, container, false)

        textview2 = vue.findViewById(R.id.textView2)
        textview3 = vue.findViewById(R.id.textView3)
        textview4 = vue.findViewById(R.id.textView4)
        textview5 = vue.findViewById(R.id.textView5)
        textview6 = vue.findViewById(R.id.textView6)
        image = vue.findViewById(R.id.imageView4)
        button = vue.findViewById(R.id.button)
        contact = vue.findViewById(R.id.contacter)

        button.setOnClickListener { presentateur.allezVersPaiement() }

        contact.setOnClickListener { presentateur.allezVersContact() }
        val arguments = arguments
        if (arguments != null) {
            val modeleSelectionne = arguments.getString("modeleSelectionne")

            modeleSelectionne?.let {
                textview2.text = "5"
                textview3.text = "Gaz"
                textview4.text = "Automatique"
                textview5.text = modeleSelectionne
                textview6.text = "Pierre"

                if (!modeleSelectionne.isNullOrEmpty()) {
                    val imageResourceId = resources.getIdentifier(modeleSelectionne.toLowerCase().replace(" ", "_"), "drawable", requireContext().packageName)
                    if (imageResourceId != 0) {
                        image.setImageResource(imageResourceId)
                        image.visibility = View.VISIBLE
                    } else {
                        image.visibility = View.GONE
                    }
                } else {
                    image.visibility = View.GONE
                }
            }
        }
        return vue
    }

    fun naviguerVersPaiement(){
        val action =
            com.example.louemonchar.détail.EcranDetailDirections.actionÉcranDétailToVuePaiement()
        findNavController().navigate(action)

    }

    fun naviguerVersContact(){
        val action =
            com.example.louemonchar.détail.EcranDetailDirections.actionÉcranDétailToContact()
        findNavController().navigate(action)
    }
}