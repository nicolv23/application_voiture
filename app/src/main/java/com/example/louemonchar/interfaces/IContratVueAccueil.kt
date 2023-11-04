package com.example.louemonchar.interfaces

interface IContratVueAccueil {
    interface Vue {
        fun setToolbarTitle(title: String)
        fun getString(resourceId: String): String
    }

    interface Presentateur {
        fun onButtonClicked()
        fun onDestroy()
    }

    object R {
        const val string_fragment_marques_auto = "fragment_marques_auto"
    }
}