package com.example.louemonchar.présentateur

import com.example.louemonchar.vue.ÉcranDétail

class DetailPresentateur(var vue: ÉcranDétail) {

    fun allezVersPaiement(){
       vue.naviguerVersPaiement()
    }
}