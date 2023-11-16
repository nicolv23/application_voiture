package com.example.louemonchar.détail

import com.example.louemonchar.détail.EcranDetail

class DetailPresentateur(var vue: EcranDetail) {

    fun allezVersPaiement(){
       vue.naviguerVersPaiement()
    }
}