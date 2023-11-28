package com.example.louemonchar.marqueauto

import com.example.louemonchar.marqueauto.IContratVueMarque

class MarquePresentateur(private val vue: IContratVueMarque.Vue) : IContratVueMarque.Presentateur {

    override fun onMarqueSelected(marque: String) {
        vue.afficherModeleVoitures(marque)
    }

    override fun setToolbarTitle() {
        val titre = "fragment_marques_auto"
        vue.setToolbarTitle(titre)
    }

    override fun enregistrerUneVoiture() {
        vue.allerVersEnregistrerUneVoiture()
    }

    override fun deconnexion() {
        vue.allezVersConnexion()
    }




}