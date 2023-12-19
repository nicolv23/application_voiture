package com.example.louemonchar.http

import java.sql.Date

data class Auto(val code: String,
                val code_propriétaire: String,
                val marque: String,
                val transmission: String,
                val modèle: String,
                val année: Int,
                val image: String,
                val etat: String,
                val prix: Int,
                val location: String = Date((1702666669000..1712666669000).random().toLong()).toString())
