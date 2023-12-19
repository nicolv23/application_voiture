package com.example.louemonchar.presentation.enregistrervoiture

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.louemonchar.R
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.louemonchar.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class EnregistrerVoitureVue : Fragment(), EnregistrerVoitureInterface.Vue {

    private lateinit var presentateur: EnregistrerVoitureInterface.Presentateur
    private val FILE_PICK_REQUEST_CODE = 123
    private lateinit var image_view : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_enregistrer__voiture, container, false)
        image_view = view.findViewById(R.id.image_réserver)
        presentateur = EnregistrerVoiturePresentateur(this)
        initComponent(view)

        return view
    }

    private fun initComponent(view: View) {

        val btnFichier: Button = view.findViewById(R.id.btnFichier)
        btnFichier.setOnClickListener{
            val fichierIntent = Intent(Intent.ACTION_GET_CONTENT)
            fichierIntent.type = "image/*"
            startActivityForResult(fichierIntent, FILE_PICK_REQUEST_CODE)
        }
        val btnCamera:Button = view.findViewById(R.id.btnCamera)
        btnCamera.setOnClickListener {
            presentateur.utilisationCamera()
        }
    }

    override fun afficherImage(image:String?){
        Glide.with(this).load(image).into(image_view)
    }

    override fun navigationVersCamera() {
        Navigation.findNavController(requireView()).navigate(R.id.vers_cameraVue)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_PICK_REQUEST_CODE && resultCode == RESULT_OK) {
            val selectedFileUri = data?.data
            Log.d("EnregistrerFragment", "Selected File URI: $selectedFileUri")
            selectedFileUri?.let {
                presentateur.onImageSelectionnee(it.toString())
            }
        }
    }




    override fun onResume() {
        super.onResume()

        // Masquer le menu de navigation dans ce fragment
        (activity as? MainActivity)?.apply {
           // hideBottomNavigation()


            val fab = requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButton)
            hideFloatingActionButton(fab)


        }
    }

    override fun onPause() {
        super.onPause()

        // Réafficher le menu de navigation en quittant ce fragment
        (activity as? MainActivity)?.apply {
            showBottomNavigation()

            val fab = requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButton)
            showFloatingActionButton(fab)
        }
    }
}
