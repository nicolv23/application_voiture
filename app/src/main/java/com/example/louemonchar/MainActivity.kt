package com.example.louemonchar

import BDVoitures
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.louemonchar.main.IContratVueMain
import com.example.louemonchar.marqueauto.ModeleListener
import com.example.louemonchar.main.MainPresentateur
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon

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

        val bdVoitures = BDVoitures(this, SourceDeVoituresBidon(this))
        bdVoitures.initialiserBD()

        // Ajoutez le Toast pour afficher un message si la base de données a été créée
        Log.d("Base de données", "La bd sqlite a été créée avec succès")
        creationBD("La base de données a été créée avec succès")

        // setupActionBarWithNavController(navController)
    }

    // Fonction pour afficher le Toast
    private fun creationBD(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
