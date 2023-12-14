package com.example.louemonchar.presentation.accueil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.louemonchar.MainActivity
import com.example.louemonchar.R
import com.google.android.material.floatingactionbutton.FloatingActionButton



class AccueilVue : Fragment(), AccueilInterface {

    private lateinit var gridView: GridView
    private val accueilModèle = AccueilModèle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_accueil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridView = view.findViewById(R.id.gridAccueil)
        val customAdapter = CustomAdapter()
        gridView.adapter = customAdapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            AccueilPresentateur(this, accueilModèle).onItemClick(position)
        }
    }

    private inner class CustomAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return accueilModèle.getNombreVoitures()

        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return accueilModèle.getNomVoiture(position)
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val view1 = LayoutInflater.from(viewGroup?.context)
                .inflate(R.layout.datadatelocation, null)

            val nom = view1.findViewById<TextView>(R.id.text_donneedatelocation)
            val logo = view1.findViewById<ImageView>(R.id.image_donneedatelocation)

            nom.text = accueilModèle.getNomVoiture(position)
            logo.setImageResource(accueilModèle.getLogoVoiture(position))

            return view1
        }
    }


    override fun onResume() {
        super.onResume()

        // Masquer le menu de navigation dans ce fragment
        (activity as? MainActivity)?.apply {
            hideBottomNavigation()


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

private fun Any.getNomVoiture() {

}
