package com.example.louemonchar.presentation.enregistrervoiture

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.louemonchar.http.Auto

class PartageVueModel :ViewModel() {
    private val nouvelle_Voiture = MutableLiveData<Auto?>()
    val nouvelleVoiture : MutableLiveData<Auto?>
        get()= nouvelle_Voiture

    fun voitureEnregistrer(voiture:Auto){
        Log.d("PartageVueModel", "Ajouter la nouvelle voiture : $voiture")
        nouvelle_Voiture.value=voiture
    }
    fun clearNouvelleVoiture() {
        Log.d("PartageVueModel", "Nouvelle voiture enregistrer")

        nouvelle_Voiture.value = null
    }
}