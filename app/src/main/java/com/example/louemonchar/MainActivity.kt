
/*

package com.example.louemonchar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.louemonchar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Trouver le NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Obtenir le NavController à partir du NavHostFragment
        navController = navHostFragment.navController


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}

 */



package com.example.louemonchar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.louemonchar.databinding.ActivityMainBinding
import com.example.louemonchar.http.Auto
import com.example.louemonchar.modèle.VoitureUiModèle
import com.example.louemonchar.presentation.connexion.ConnexionVue
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    internal lateinit var liste: List<Auto>


    fun showBottomNavigation() {
        binding?.bottomNavigationView?.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding?.bottomNavigationView?.visibility = View.GONE
    }

    fun showFloatingActionButton(fab: FloatingActionButton?) {
        fab?.visibility = View.VISIBLE
    }

    fun hideFloatingActionButton(fab: FloatingActionButton?) {
        fab?.visibility = View.GONE
    }





    private lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        getAllData()

        binding?.bottomNavigationView?.background = null

        binding?.bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(R.id.accueilVue)
               // R.id.deconnecter -> replaceFragment(R.id.connexionVue)
                R.id.enregistrer -> replaceFragment(R.id.reserverVoitureFragment)
                R.id.disponible -> replaceFragment(R.id.voituresDisponiblesVue)
                R.id.favoris -> replaceFragment(R.id.favorisFragment)

            }
            true
        }


        binding?.floatingActionButton?.setOnClickListener {
            replaceFragment(R.id.enregistrerVoitureVue)
        }


        // Trouver le NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Obtenir le NavController à partir du NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun replaceFragment(destinationId: Int) {
        navController.navigate(destinationId)
    }
    private fun getAllData() {
        Api.retrofitService.getAllAuto().enqueue(object: Callback<List<Auto>> {
            override fun onResponse(
                call: Call<List<Auto>>,
                response: Response<List<Auto>>
            ){
                if(response.isSuccessful){

                    liste = response.body()!!
                    Toast.makeText(this@MainActivity, "Connexion HTTP Reussi", Toast.LENGTH_SHORT).show()



                }
            }
            override fun onFailure(
                call: Call<List<Auto>>,
                t: Throwable
            ){
                t.printStackTrace()
                Toast.makeText(this@MainActivity, "Pas de connexion HTTP", Toast.LENGTH_SHORT).show()
                liste = emptyList()

            }
        })
    }

}


