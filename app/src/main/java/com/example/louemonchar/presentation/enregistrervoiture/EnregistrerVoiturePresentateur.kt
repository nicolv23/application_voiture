package com.example.louemonchar.presentation.enregistrervoiture

class EnregistrerVoiturePresentateur (private val vue : EnregistrerVoitureInterface.Vue): EnregistrerVoitureInterface.Presentateur {

    private val modele : EnregistrerVoitureInterface.Modele  = EnregistrerVoitureModele()
    private var dateLocation: java.util.Date? = null

    override fun utilisationCamera() {
        vue.navigationVersCamera()
    }

    override fun onImageSelectionnee(imageURI: String) {
        vue.afficherImage(imageURI)
    }

    override fun setDateLocation(date: java.util.Date) {
        dateLocation = date
    }




}
