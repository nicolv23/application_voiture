package com.example.louemonchar.presentation.intro

class IntroPresentateur(private val vue: IntroInterface.Vue) : IntroInterface.Presentateur {

    private val modele: IntroInterface.Modele = IntroModele()

    override fun commencerAnimation() {
        vue.afficherEcranPrincipal()
    }
}
