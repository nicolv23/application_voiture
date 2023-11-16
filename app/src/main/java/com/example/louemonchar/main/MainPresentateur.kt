package com.example.louemonchar.main

import com.example.louemonchar.main.IContratVueMain

class MainPresentateur(private var vue: IContratVueMain.Vue?) : IContratVueMain.Presentateur {

    override fun onViewCreated() {

    }

    override fun onDestroy() {
        vue = null
    }

}