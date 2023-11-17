package com.example.louemonchar.marqueauto

interface IContratVueMarque {
    interface Vue {
        fun afficherModeleVoitures(marque: String)
        fun setToolbarTitle(titre: String)
    }

    interface Presentateur {
        fun onMarqueSelected(marque: String)
        fun setToolbarTitle()
    }
}