package com.example.louemonchar.présentateur

import com.example.louemonchar.vue.EcranDetail

class DetailPresentateur(var vue: EcranDetail) {

    fun allezVersPaiement(){
       vue.naviguerVersPaiement()
    }
}