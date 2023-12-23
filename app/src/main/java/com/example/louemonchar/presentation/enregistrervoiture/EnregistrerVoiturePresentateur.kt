package com.example.louemonchar.presentation.enregistrervoiture

import android.util.Log
import android.widget.Toast
import com.example.louemonchar.http.Auto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnregistrerVoiturePresentateur (private val vue : EnregistrerVoitureInterface.Vue): EnregistrerVoitureInterface.Presentateur {

    private val modele : EnregistrerVoitureInterface.Modele  = EnregistrerVoitureModele()
    private var dateLocation: java.util.Date? = null

    override fun utilisationCamera() {
        vue.navigationVersCamera()
    }

    override fun onImageSelectionnee(imageURI: String) {
        vue.afficherImage(imageURI)
    }

    override fun mettreDate(date: java.util.Date) {
        dateLocation = date
    }

    override fun enregistrerNouvelleVoiture(voiture: Auto) {
        modele.enregistrerNouvelleVoiture(voiture, object :
            Callback<Auto> {
            override fun onResponse(
                call: Call<Auto>,
                response: Response<Auto>
            ) {
                reponse(response)
            }

            override fun onFailure(
                call: Call<Auto>,
                msgErreur: Throwable
            ) {
                erreur(msgErreur)
            }
        })
    }



    private fun reponse(response: Response<Auto>) {
        if (response.isSuccessful) {
            messageErreur("Voiture enregistrée avec succès")
        } else {
            messageErreur("Erreur lors de l'enregistrement de la voiture")
        }
    }

    private fun erreur(msgErreur: Throwable) {
        messageErreur("Erreur lors de la connexion au serveur")
        Log.e(TAG, "Erreur connexion au server", msgErreur)
    }

    private fun messageErreur(message: String) {
        Toast.makeText(vue.requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "EnregistrerVoiturePresentateur"
    }


}



