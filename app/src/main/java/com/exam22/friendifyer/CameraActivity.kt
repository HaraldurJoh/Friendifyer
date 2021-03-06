package com.exam22.friendifyer

import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Insets.add
import android.net.Uri
import android.nfc.Tag
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.ActionProvider
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.exam22.friendifyer.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_camera.*
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.jar.Manifest

class CameraActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private  var imageCapture: ImageCapture? = null

    private lateinit var cameraExecutor: ExecutorService

    private var isFrontCamera: Boolean= false
    private var picturePath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_camera)

        //Camera permissions
        if (allPermissionsGranted()){
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS )
        }
        btnTakePhoto.setOnClickListener { onClickPhoto() }
        btnSavePhoto.setOnClickListener{ onClickSave() }
        btnExitPhoto.setOnClickListener{ onClickExit () }
        switchFlip.setOnCheckedChangeListener { compoundButton, b ->
            isFrontCamera = b
            startCamera()
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun onClickSave() {
        val data = Intent().apply { putExtra("friendPhoto", picturePath) }
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    private fun onClickPhoto() {
        btnSavePhoto.visibility = View.VISIBLE

        val imageCapture = imageCapture?: return

        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues(). apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues)
            .build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "failed to capture Photo: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val msg = "Photo capture succeeded: ${output.savedUri}"
                    picturePath = "${output.savedUri}"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, msg)
                    setPicture()
                }
            }
        )
    }

    private fun setPicture() {
        if (!picturePath.contentEquals("")) {
            viewFinder.visibility = View.INVISIBLE
            Log.d(TAG, "$picturePath: ER PATH2!!")
            val uri: Uri= Uri.parse(picturePath)
            takenPicture.setImageURI(uri)
            takenPicture.visibility= View.VISIBLE
            Log.d(TAG, "$picturePath: ER PATH1!!")
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.createSurfaceProvider())
                }

            imageCapture = ImageCapture.Builder()
                .build()

            if (isFrontCamera) {
                useFrontOrBackCamera(CameraSelector.DEFAULT_FRONT_CAMERA, cameraProvider, preview)

            } else {
                useFrontOrBackCamera(CameraSelector.DEFAULT_BACK_CAMERA, cameraProvider, preview)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun useFrontOrBackCamera(defaultBackCamera: CameraSelector, cameraProvider: ProcessCameraProvider, preview: Preview) {

        val cameraSelector = defaultBackCamera

        try {
            cameraProvider.unbindAll()

            cameraProvider.bindToLifecycle(
                this, cameraSelector, preview, imageCapture
            )

        } catch (exc: Exception) {
            Log.e(TAG, "Use case bindind failed", exc)
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext,it ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "PhotoActivity"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf (
                android.Manifest.permission.CAMERA
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            }else {
                Toast.makeText(this,
                    "Permissions not granted by the user,",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun onClickExit() {
        if (!picturePath.contentEquals("")) {
            picturePath = ""
            takenPicture.visibility = View.INVISIBLE
            viewFinder.visibility = View.VISIBLE
            btnSavePhoto.visibility = View.VISIBLE
        } else {
            finish()
        }
    }
}

