package com.example.arnolibs.arActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.arnolibs.R
import com.google.ar.sceneform.ux.ArFragment

class ArActivity : AppCompatActivity() {
    private var arFragment: ArFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        arFragment =
            supportFragmentManager.findFragmentById(R.id.scene_form_fragment) as ArFragment?
    }
}