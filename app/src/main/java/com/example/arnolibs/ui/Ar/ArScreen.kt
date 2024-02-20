package com.example.arnolibs.ui.Ar

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.arnolibs.navigation.Destination

@Composable
fun ArScreen(
    navigate: (Destination) -> Unit,
    viewModel: ArViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val context = LocalContext.current
    val permissionCamera = android.Manifest.permission.CAMERA
    val launcherCam = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()
    ){isGranted: Boolean ->
        if (isGranted) {

            Log.d("ExampleScreen", "PERMISSION GRANTED")
            viewModel.createSession(context = context)
        } else {
            // Permissão Negada: Faça algo
            Log.d("ExampleScreen", "PERMISSION DENIED")
        }
    }


    Column(Modifier.fillMaxSize()) {

        Button(onClick = { launcherCam.launch(permissionCamera) }) {
           Text(text = "OPEN")
        }
    }
}