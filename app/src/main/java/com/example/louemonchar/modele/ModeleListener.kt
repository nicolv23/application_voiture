package com.example.louemonchar.modele

interface ModeleListener {
    fun onModeleEnregistre(marque: String, modele: String)
    fun getModelesEnregistres(marque: String): List<String>
}
