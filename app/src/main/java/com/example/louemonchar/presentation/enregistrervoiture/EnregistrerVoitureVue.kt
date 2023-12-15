package com.example.louemonchar.presentation.enregistrervoiture

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.louemonchar.R
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.louemonchar.MainActivity
import com.example.louemonchar.databinding.FragmentEnregistrerVoitureBinding
import com.example.louemonchar.databinding.FragmentVoituresDisponiblesBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Date
import java.util.Locale


class EnregistrerVoitureVue : Fragment(), EnregistrerVoitureInterface.Vue {

    private lateinit var presentateur: EnregistrerVoitureInterface.Presentateur
    private val FILE_PICK_REQUEST_CODE = 123
    private lateinit var image_view : ImageView
    val REQUEST_CODE_PERMISSIONS = 10
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    private lateinit var binding: FragmentEnregistrerVoitureBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnregistrerVoitureBinding.inflate(inflater, container, false)
       image_view = binding.imageView.findViewById(R.id.image_view)
        presentateur = EnregistrerVoiturePresentateur(this)
        setupListeners()
        val btnFichier: Button = binding.btnFichier.findViewById(R.id.btnFichier)
        btnFichier.setOnClickListener {
            val fichierIntent = Intent(Intent.ACTION_GET_CONTENT)
            fichierIntent.type = "image/*"
            startActivityForResult(fichierIntent, FILE_PICK_REQUEST_CODE)
        }
        val btnCamera: Button = binding.btnCamera.findViewById(R.id.btnCamera)
        btnCamera.setOnClickListener {
            if (allPermissionsGranted()) {
                presentateur.utilisationCamera()
            }else
            {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
            }
        }

        return binding.root
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

    private fun allPermissionsGranted(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            presentateur.utilisationCamera()
        }
    }

    private fun setupListeners() {
        binding.choisirDate.setOnClickListener {
            showDatePickerDialog { selectedDate ->
                presentateur.setDateLocation(selectedDate)
                binding.date.setText(selectedDate.toString())
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
}

