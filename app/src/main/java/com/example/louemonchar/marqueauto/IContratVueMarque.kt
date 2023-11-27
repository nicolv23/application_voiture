package com.example.louemonchar.marqueauto

interface IContratVueMarque {
    interface Vue {
        fun afficherModeleVoitures(marque: String)
        fun setToolbarTitle(titre: String)
       fun allezVersConnexion()

       fun allerVersEnregistrerUneVoiture()
    }

    interface Presentateur {
        fun onMarqueSelected(marque: String)
        fun setToolbarTitle()
        fun enregistrerUneVoiture()
        fun deconnexion()
    }
}