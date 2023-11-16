package com.example.louemonchar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.louemonchar.main.IContratVueMain
import com.example.louemonchar.marqueauto.ModeleListener
import com.example.louemonchar.main.MainPresentateur

class MainActivity : AppCompatActivity(), IContratVueMain.Vue, ModeleListener {

    private lateinit var navController: NavController
    private lateinit var presentateur: IContratVueMain.Presentateur

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presentateur = MainPresentateur(this)
        presentateur.onViewCreated()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onDestroy() {
        presentateur.onDestroy()
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onModeleEnregistre(marque: String, modele: String) {
    }

    override fun getModelesEnregistres(marque: String): List<String> {
        return emptyList()
    }
}
