package com.example.louemonchar.vue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.louemonchar.R
import com.example.louemonchar.présentateur.DetailPresentateur

class ÉcranDétail : Fragment() {

    lateinit var navController: NavController
    lateinit var textview1: TextView
    lateinit var textview2: TextView
    lateinit var textview3: TextView
    lateinit var textview4: TextView
    lateinit var textview5: TextView
    lateinit var textview6: TextView
    lateinit var image: ImageView
    lateinit var button: Button
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

        button.setOnClickListener { presentateur.allezVersPaiement() }

        val arguments = arguments
        if (arguments != null) {
            val modeleSelectionne = arguments.getString("modeleSelectionne")

            textview2.text = "5"
            textview3.text = "Gaz"
            textview4.text = "Automatique"
            textview5.text = "$modeleSelectionne"
            textview6.text = "Pierre"

            val imageResourceId = resources.getIdentifier(modeleSelectionne, "drawable", requireContext().packageName)
            if (imageResourceId != 0) {
                image.setImageResource(imageResourceId)
            } else {
                image.visibility = View.GONE
            }
        }

        return vue
    }

    fun naviguerVersPaiement(){
        val action = ÉcranDétailDirections.actionÉcranDétailToVuePaiement()
        findNavController().navigate(action)

    }
}