package com.example.louemonchar.main

interface IContratVueMain {
    interface Vue {

    }

    interface Presentateur {
        fun onViewCreated()
        fun onDestroy()
    }

}