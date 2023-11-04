package com.example.louemonchar.présentateur

import com.example.louemonchar.interfaces.IContratVueMarque

class MarquePresentateur(private val vue: IContratVueMarque.Vue) : IContratVueMarque.Presentateur {

    override fun onMarqueSelected(marque: String) {
        vue.afficherModeleVoitures(marque)
    }

    override fun setToolbarTitle() {
        val titre = "fragment_marques_auto"
        vue.setToolbarTitle(titre)
    }


}