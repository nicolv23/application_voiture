package com.example.louemonchar.détail

import com.example.louemonchar.détail.EcranDetail

class DetailPresentateur(var vue: EcranDetail) {

    fun allezVersPaiement(){
       vue.naviguerVersPaiement()
    }

    fun allezVersContact() {
        vue.naviguerVersContact()
    }
    fun allezVersMarques(){
        vue.marque()
    }
}