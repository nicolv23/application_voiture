package com.example.louemonchar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.louemonchar.modèle.ListeVoituresEnregistres
import com.example.louemonchar.interfaces.IContratVueMain
import com.example.louemonchar.présentateur.MainPresentateur

class MainActivity : AppCompatActivity(), IContratVueMain.Vue {
    private lateinit var navController: NavController
    private lateinit var favorisViewModel: ListeVoituresEnregistres
    private lateinit var presentateur: IContratVueMain.Presentateur

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presentateur = MainPresentateur(this)
        presentateur.onViewCreated()

        favorisViewModel = ViewModelProvider(this).get(ListeVoituresEnregistres::class.java)
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
}