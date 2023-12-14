package com.example.louemonchar.presentation.intro

class IntroPresentateur(private val vue: IntroInterface.Vue) : IntroInterface.Presentateur {

    private val modèle: IntroInterface.Modele = IntroModèle()

    override fun commencerAnimation() {
        vue.afficherEcranPrincipal()
    }
}
