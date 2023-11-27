package com.example.louemonchar.présentation.EnregistrerVoiture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface enregistrerVoitureInterface {

    interface Vue {
        fun navigationVersCamera()
        fun afficherImage(image:String?)
        fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }

    interface Presentateur {
        fun utilisationCamera ()
        fun onImageSelectionnee(imageURI:String)

    }

    interface Modele {
        fun enregistrerVoiture()


    }
}