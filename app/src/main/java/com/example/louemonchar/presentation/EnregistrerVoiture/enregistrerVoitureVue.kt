package com.example.louemonchar.présentation.EnregistrerVoiture

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.louemonchar.R
import androidx.navigation.Navigation
import com.bumptech.glide.Glide


class enregistrerVoitureVue : Fragment(), enregistrerVoitureInterface.Vue {

  private lateinit var presentateur: enregistrerVoitureInterface.Presentateur
    private val FILE_PICK_REQUEST_CODE = 123
    private lateinit var image_view : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_enregistrer__voiture_, container, false)
        image_view = view.findViewById(R.id.image_view)
        presentateur = enregistrerVoiturePresentateur(this)
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
        Navigation.findNavController(requireView()).navigate(R.id.action_enregistrer_Voiture_Fragment_to_navCameraFragment)
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
}