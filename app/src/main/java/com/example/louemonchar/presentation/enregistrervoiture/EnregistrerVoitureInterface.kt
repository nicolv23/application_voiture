package com.example.louemonchar.presentation.enregistrervoiture

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.louemonchar.http.Auto

import retrofit2.Callback


interface EnregistrerVoitureInterface {

    interface Vue {
        fun navigationVersCamera()
        fun afficherImage(image:String?)
        fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

        fun requetePermission(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)

         fun montrerBarreChargement()

         fun cacherBarreChargement()
        fun requireContext(): Context?
    }

    interface Presentateur {
        fun utilisationCamera ()
        fun onImageSelectionnee(imageURI:String)
        fun mettreDate(date: java.util.Date)

        fun enregistrerNouvelleVoiture(voiture: Auto)

    }

    interface Modele {
        fun enregistrerNouvelleVoiture(voiture: Auto, callback: Callback<Auto>)
     }
}
