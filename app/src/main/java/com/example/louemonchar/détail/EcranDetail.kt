package com.example.louemonchar.détail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.louemonchar.R
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon

class EcranDetail : Fragment() {

    lateinit var textview2: TextView
    lateinit var textview3: TextView
    lateinit var textview4: TextView
    lateinit var textview5: TextView
    lateinit var textview6: TextView
    lateinit var image: ImageView
    lateinit var button: Button
    lateinit var contact: Button
    private val sourceDeVoitures = SourceDeVoituresBidon()

    private lateinit var presentateur: DetailPresentateur

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentateur = DetailPresentateur()
    }

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

        button.setOnClickListener {
            val navController = findNavController()
            presentateur.allezVersPaiement(navController)
        }

        contact.setOnClickListener {
            arguments?.getString("modeleSelectionne")?.let { modeleSelectionne ->
                val navController = findNavController()
                presentateur.allezVersContact(navController, modeleSelectionne)
            }
        }

        val arguments = arguments
        if (arguments != null) {
            val modeleSelectionne = arguments.getString("modeleSelectionne")

            modeleSelectionne?.let {
                Log.d("EcranDetail", "Modèle sélectionné : $modeleSelectionne")
                val proprietaire = sourceDeVoitures.obtenirProprietaire(modeleSelectionne)
                val detailsProprietaire = proprietaire?.let {
                    sourceDeVoitures.obtenirDetailsProprietaire(it.nom)
                }


                // Affichez les détails de la voiture
                textview2.text = "5"
                textview3.text = "Gaz"
                textview4.text = "Automatique"
                textview5.text = modeleSelectionne
                textview6.text = detailsProprietaire?.nom ?: ""

                // Affichez l'image de la voiture
                if (!modeleSelectionne.isNullOrEmpty()) {
                    val imageResourceId = resources.getIdentifier(
                        modeleSelectionne.toLowerCase().replace(" ", "_"),
                        "drawable",
                        requireContext().packageName
                    )
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
}