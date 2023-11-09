package com.example.louemonchar.présentateur

import com.example.louemonchar.interfaces.IContratVueAccueil

class AccueilPresentateur(private val vue: IContratVueAccueil.Vue) : IContratVueAccueil.Presentateur {

    override fun onButtonClicked() {
        val titre = vue.getString(IContratVueAccueil.R.string_fragment_marques_auto)
        vue.setToolbarTitle(titre)
    }

    override fun onDestroy() {

    }
}