package com.example.louemonchar.présentateur

import com.example.louemonchar.interfaces.IContratVueMain

class MainPresentateur(private var vue: IContratVueMain.Vue?) : IContratVueMain.Presentateur {

    override fun onViewCreated() {

    }

    override fun onDestroy() {
        vue = null
    }

}