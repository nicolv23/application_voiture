package com.example.louemonchar.presentation.enregistrervoiture

class EnregistrerVoiturePresentateur (private val vue : EnregistrerVoitureInterface.Vue): EnregistrerVoitureInterface.Presentateur {

    private val modele : EnregistrerVoitureInterface.Modele  = EnregistrerVoitureModele()

    override fun utilisationCamera() {
        vue.navigationVersCamera()
    }

    override fun onImageSelectionnee(imageURI: String) {
        vue.afficherImage(imageURI)
    }

}
