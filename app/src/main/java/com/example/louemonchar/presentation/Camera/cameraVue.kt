package com.example.louemonchar.présentation.Camera

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
import com.example.louemonchar.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class cameraVue : Fragment(),cameraInterface.Vue {
    private lateinit var viewBinding: FragmentNavCameraBinding
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var viewFinder: PreviewView
    private lateinit var imageCapture: ImageCapture
    private lateinit var presentateur: cameraPresentateur
    val REQUEST_CODE_PERMISSIONS = 10
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        val modele = cameraModele(requireContext())
        presentateur = cameraPresentateur(modele,this,requireContext())
        job = CoroutineScope( Dispatchers.IO ).launch {
            if (allPermissionsGranted()) {
                ouvrirCamera()
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
            }
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
        val btnRetour : Button = view.findViewById(R.id.retour)

        btnRetour.setOnClickListener {
            presentateur.retourVersEnregistrement()
        }

    }

    override fun retour() {
        Navigation.findNavController(requireView()).navigate(R.id.action_navCameraFragment_to_enregistrer_Voiture_Fragment)
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

    private fun allPermissionsGranted(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            job = CoroutineScope( Dispatchers.IO ).launch {
                ouvrirCamera()
            }
        }
    }

    override fun messagePhotoSauvegarder(savedUri: Uri) {
        val msg = "CAPTURE PHOTO AVEC SUCCES"
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        Log.d(ContentValues.TAG, msg)
    }

    override fun messageErreurSauvegarder(errorMessage: String) {
        Log.e(ContentValues.TAG, "CAPTURE PHOTO N'A PAS FONCTIONNÉ")
    }


}