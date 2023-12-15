package com.example.louemonchar.presentation.enregistrervoiture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface EnregistrerVoitureInterface {

    interface Vue {
        fun navigationVersCamera()
        fun afficherImage(image:String?)
        fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)

    }

    interface Presentateur {
        fun utilisationCamera ()
        fun onImageSelectionnee(imageURI:String)
        fun setDateLocation(date: java.util.Date)

    }

    interface Modele {
        fun enregistrerVoiture()


    }
}
