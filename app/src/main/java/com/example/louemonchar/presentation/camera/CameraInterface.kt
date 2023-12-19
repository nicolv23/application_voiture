package com.example.louemonchar.presentation.camera

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.camera.view.PreviewView

interface CameraInterface {

    interface Vue{
        fun retour()
        fun ouvrirCamera()
        fun messagePhotoSauvegarder(savedUri: Uri)
        fun messageErreurSauvegarder(errorMessage:String)
        fun onCreate(savedInstanceState: Bundle?)
        fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View?
        fun onViewCreated(view: View, savedInstanceState: Bundle?)
        }

    interface Presentateur {
        fun prendrePhoto(imageCapture:ImageCapture)
        fun retourVersEnregistrement()

    }

    interface Modele {
        fun enregistrerPhoto(photoUri:Uri)
    }

}
