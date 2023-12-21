package com.example.louemonchar.presentation.enregistrervoiture

import android.app.Activity.RESULT_OK

import android.Manifest
import android.app.DatePickerDialog

import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.louemonchar.R
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.louemonchar.MainActivity
import com.example.louemonchar.databinding.FragmentEnregistrerVoitureBinding
import com.example.louemonchar.http.Auto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale

class EnregistrerVoitureVue : Fragment(), EnregistrerVoitureInterface.Vue {
    private val partageVueModel: PartageVueModel by viewModels()

    private lateinit var presentateur: EnregistrerVoitureInterface.Presentateur
    private val FILE_PICK_REQUEST_CODE = 123
    private lateinit var image_view : ImageView
    val REQUEST_CODE_PERMISSIONS = 10
    val REQUETE_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    private lateinit var binding: FragmentEnregistrerVoitureBinding
    private var imageSelectionné: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // val view = inflater.inflate(R.layout.fragment_enregistrer__voiture, container, false)

        binding = FragmentEnregistrerVoitureBinding.inflate(inflater, container, false)
       image_view = binding.imageView.findViewById(R.id.image_view)

        presentateur = EnregistrerVoiturePresentateur(this)

        setupListeners()

        val btnFichier: Button = binding.btnFichier.findViewById(R.id.btnFichier)
        btnFichier.setOnClickListener {
            montrerBarreChargement()
            lifecycleScope.launch {
                // Simulation de chargement pendant 2 secondes
                delay(2000)
            val fichierIntent = Intent(Intent.ACTION_GET_CONTENT)
            fichierIntent.type = "image/*"
            startActivityForResult(fichierIntent, FILE_PICK_REQUEST_CODE)
                cacherBarreChargement()
                afficherMessageChargementTermine()
            }
        }

        val btnCamera: Button = binding.btnCamera.findViewById(R.id.btnCamera)
        btnCamera.setOnClickListener {
            if (allPermissionsGranted()) {
                presentateur.utilisationCamera()
            }else
            {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    REQUETE_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
            }
        }

        val btnEnregister:Button = binding.btnEnregistrer.findViewById(R.id.btnEnregistrer)
        btnEnregister.setOnClickListener{

            montrerBarreChargement()
            lifecycleScope.launch {

                delay(2000)

                val code = binding.id.toString()
                val code_propriétaire= binding.IdPropio.toString()
                val marque = binding.marque.text.toString()
                val transmission = binding.transmission.text.toString()
                val modele = binding.modele.text.toString()
                val annee = binding.annee.text.toString().toIntOrNull() ?: 0
                val image = imageSelectionné?.toString() ?: ""
                val etat = binding.etat.text.toString()
                val prix = binding.prix.text.toString().toIntOrNull() ?: 0
                val locationDate = binding.choisirDate.toString()

                val nouvelleVoiture = Auto(
                  code,code_propriétaire,marque,transmission,modele , annee,image,etat,prix,locationDate)
                   presentateur.enregistrerNouvelleVoiture(nouvelleVoiture)

               // partageVueModel.voitureEnregistrer(nouvelleVoiture)
               cacherBarreChargement()
                afficherMessageChargementTermine()
            }
        }

        return binding.root
    }

    override fun afficherImage(image:String?){
        Glide.with(this).load(image).into(image_view)
    }


    override fun navigationVersCamera() {
        montrerBarreChargement()
        lifecycleScope.launch {
            delay(2000)
        Navigation.findNavController(requireView()).navigate(R.id.vers_cameraVue)
            cacherBarreChargement()
            afficherMessageChargementTermine()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_PICK_REQUEST_CODE && resultCode == RESULT_OK) {
            val selectedFileUri = data?.data
            Log.d("EnregistrerFragment", "Selected File URI: $selectedFileUri")
            selectedFileUri?.let {
                imageSelectionné = it
                Glide.with(this).load(imageSelectionné).into(binding.imageView)

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
//Donne les permissions
    private fun allPermissionsGranted(): Boolean {
        return REQUETE_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
        }
    }
//Requete de permission
    override fun requetePermission(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            presentateur.utilisationCamera()
        }
    }

    private fun setupListeners() {
        binding.choisirDate.setOnClickListener {
            montrerBarreChargement()
            lifecycleScope.launch {
                delay(2000)
            showDatePickerDialog { selectedDate ->
                presentateur.mettreDate(selectedDate)
                binding.date.setText(selectedDate.toString())
                cacherBarreChargement()
                afficherMessageChargementTermine()
            }
            }
        }
    }
    private fun showDatePickerDialog(onDateSelected: (Date) -> Unit) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                val date = format.parse("$year-${monthOfYear + 1}-$dayOfMonth")
                date?.let { onDateSelected(it) }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }


    override fun montrerBarreChargement() {
        binding.barreProgression.visibility = View.VISIBLE
    }

    override fun cacherBarreChargement() {
        binding.barreProgression.visibility = View.GONE
    }
    private fun afficherMessageChargementTermine() {
        Snackbar.make(binding.root, "Chargement en cours...", Snackbar.LENGTH_SHORT).show()
    }


}

