package com.example.arnolibs.ui.Main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.arnolibs.navigation.Destination

@Composable
fun MainScreen(navigate: (Destination) -> Unit,
               ){
    
    Column(Modifier.fillMaxSize()) {
        Button(onClick = {
            navigate(Destination.Ar())
        }) {
            Text(text = "ArCore")
            
        }
    }
}