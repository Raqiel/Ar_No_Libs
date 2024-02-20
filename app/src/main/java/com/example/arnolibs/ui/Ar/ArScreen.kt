package com.example.arnolibs.ui.Ar

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import com.example.arnolibs.arActivity.ArActivity
import com.example.arnolibs.navigation.Destination
import com.google.ar.sceneform.ux.ArFragment
import kotlinx.coroutines.coroutineScope


@Composable
fun ArScreen(
    navigate: (Destination) -> Unit,
    viewModel: ArViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {


    var connected by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val permissionCamera = android.Manifest.permission.CAMERA
    val launcherCam = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {

            Log.d("ExampleScreen", "PERMISSION GRANTED")
            val sessionCreated = viewModel.createSession(context = context)
            if (sessionCreated) {
                connected = true

            } else {
                println("A criação da sessão falhou.")
            }
        } else {
            // Permissão Negada: Faça algo
            Log.d("ExampleScreen", "PERMISSION DENIED")
        }
    }



    if (connected){
        val intent = Intent(context, ArActivity::class.java)
        context.startActivity(intent)
    }
    Column(Modifier.fillMaxSize()) {

        Button(onClick = { launcherCam.launch(permissionCamera) }) {
            Text(text = "OPEN")
        }
    }
}

@Composable
fun ArView() {
    AndroidView(factory = { context ->
        ArFragment().apply {
            (context as FragmentActivity).supportFragmentManager.beginTransaction()
                .add(this, "arFragment").commit()
        }.arSceneView
    })
}