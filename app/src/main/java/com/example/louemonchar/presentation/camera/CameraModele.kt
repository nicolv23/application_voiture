package com.example.louemonchar.presentation.camera

import java.text.SimpleDateFormat
import java.util.Locale
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import android.net.Uri
import android.os.Build
class CameraModele (private val context: Context): CameraInterface.Modele {
    val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

    override fun enregistrerPhoto(photoUri: Uri) {
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }
        val contentResolver = context.contentResolver
        try {
            val savedUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            contentResolver.openOutputStream(savedUri!!)?.use { outputStream ->
                contentResolver.openInputStream(photoUri)?.use { inputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            Log.d(TAG, "Photo saved to media library: $savedUri")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to save photo to media library", e)
        }
    }






}
