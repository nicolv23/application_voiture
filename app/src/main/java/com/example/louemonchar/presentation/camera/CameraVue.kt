package com.example.louemonchar.presentation.camera

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.louemonchar.databinding.FragmentNavCameraBinding
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.louemonchar.MainActivity
import com.example.louemonchar.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraVue : Fragment(),CameraInterface.Vue {
    private lateinit var viewBinding: FragmentNavCameraBinding
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var viewFinder: PreviewView
    private lateinit var imageCapture: ImageCapture
    private lateinit var presentateur: CameraPresentateur
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        val modele = CameraModele(requireContext())
        presentateur = CameraPresentateur(modele,this,requireContext())
        job = CoroutineScope( Dispatchers.IO ).launch {

                ouvrirCamera()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNavCameraBinding.inflate(inflater, container, false)
        viewFinder = viewBinding.root.findViewById(R.id.viewFinder)
        val photo: Button = viewBinding.root.findViewById(R.id.photo)

        photo.setOnClickListener {

            presentateur.prendrePhoto(imageCapture)

        }


        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
        /*
        val btnRetour : Button = view.findViewById(R.id.retour)

        btnRetour.setOnClickListener {
            presentateur.retourVersEnregistrement()
        }

         */

    }

    override fun retour() {
        Navigation.findNavController(requireView()).navigate(R.id.vers_enregistrerVoitureVue)
    }

    override fun ouvrirCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder()
                .build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner, cameraSelector, preview, imageCapture
                )
            } catch (exc: Exception) {
                Log.e(ContentValues.TAG, "Binding ne pas fonctionner", exc)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    override fun messagePhotoSauvegarder(savedUri: Uri) {
        val msg = "CAPTURE PHOTO AVEC SUCCES"
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        Log.d(ContentValues.TAG, msg)
    }

    override fun messageErreurSauvegarder(errorMessage: String) {
        Log.e(ContentValues.TAG, "CAPTURE PHOTO N'A PAS FONCTIONNÉ")
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
