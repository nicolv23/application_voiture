package com.example.louemonchar.presentation.recuperation

class RecuperationPresentateur(private val vue: RecuperationInterface.Vue) :
    RecuperationInterface.Presentateur {

    private val modele: RecuperationInterface.Modele = RecuperationModele()

    override fun onClickLancerGPS(depart: String, destination: String) {
        if (depart.isEmpty() || destination.isEmpty()) {
            vue.afficherErreur("Il faut entrer le départ et la destination")
        } else {
            vue.lancerNavigation(depart, destination)
        }
    }

    override fun allezConnexion(){
        vue.connexion()
    }
}
