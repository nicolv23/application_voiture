package com.example.louemonchar.présentation.EnregistrerVoiture

class enregistrerVoiturePresentateur (private val vue : enregistrerVoitureInterface.Vue): enregistrerVoitureInterface.Presentateur {

    private val modele : enregistrerVoitureInterface.Modele  = enregistrerVoitureModele()

    override fun utilisationCamera() {
        vue.navigationVersCamera()
    }

    override fun onImageSelectionnee(imageURI: String) {
        vue.afficherImage(imageURI)
    }

}