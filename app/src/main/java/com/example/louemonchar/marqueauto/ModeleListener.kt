package com.example.louemonchar.marqueauto

interface ModeleListener {
    fun onModeleEnregistre(marque: String, modele: String)
    fun getModelesEnregistres(marque: String): List<String>
}
