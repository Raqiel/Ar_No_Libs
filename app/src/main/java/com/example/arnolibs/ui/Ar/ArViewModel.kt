package com.example.arnolibs.ui.Ar

import android.app.Activity
import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.core.exceptions.FatalException
import com.google.ar.core.exceptions.UnavailableException
import java.util.jar.Manifest

class ArViewModel() : ViewModel() {

    private var session: Session? = null


    init {
//        maybeEnableArButton()
    }

    fun maybeEnableArButton(context: Context) {
        ArCoreApk.getInstance().checkAvailabilityAsync(context) { availability ->
            if (availability.isSupported) {
                Log.d(TAG, "isSupported")
            } else { // The device is unsupported or unknown.
                Log.d(TAG, "unsupported or unknown")
            }
        }
    } fun createSession(context: Context):Boolean {


        if (ArCoreApk.getInstance().checkAvailability(context) == ArCoreApk.Availability.SUPPORTED_INSTALLED) {
            try {
                session = Session(context)
                val config = Config(session)
                // Do feature-specific operations here, such as enabling depth or turning on
                // support for Augmented Faces.
                session?.configure(config)
                return true

            } catch (e: UnavailableException) {
                Log.d(TAG, "UnavailableException ")
                return false
            } catch (e: FatalException) {
                Log.d(TAG, "FatalException: ARCore encountered a severe error.")
                return false
            }
        } else {
            Log.d(TAG, "Handle ARCore not supported or not installed scenarios here ")
            // Handle ARCore not supported or not installed scenarios here.
            return false
        }
    }

    //verifica o dispositivo suporta ARcore
    fun isARCoreSupportedAndUpToDate(context: Context): Boolean {
        return when (ArCoreApk.getInstance().checkAvailability(context)) {

            //AR instalado no dispositivo
            ArCoreApk.Availability.SUPPORTED_INSTALLED -> true
            //não está instalado ou a versão é muito antiga
            ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD, ArCoreApk.Availability.SUPPORTED_NOT_INSTALLED -> {
                try {
                    //solicita instalação ou atualização se necessario
                    when (ArCoreApk.getInstance().requestInstall(context as Activity?, true)) {
                        ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                            Log.i(TAG, "ARCore installation requested.")
                            false
                        }
                        //instalação ou atualização feita retorna true
                        ArCoreApk.InstallStatus.INSTALLED -> true
                    }
                } catch (e: UnavailableException) {
                    Log.e(TAG, "ARCore not installed", e)
                    false
                }
            }

            ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE ->
                // This device is not supported for AR.
                false

            ArCoreApk.Availability.UNKNOWN_CHECKING -> {

                Log.d(TAG, "unknow checking")
                false
            }

            ArCoreApk.Availability.UNKNOWN_ERROR, ArCoreApk.Availability.UNKNOWN_TIMED_OUT -> {
                Log.d(TAG, "unknow timeout")
                false
            }
        }
    }


//    fun createSession() {
//        // Create a new ARCore session.
//        session = Session(this)
//
//        // Create a session config.
//        val config = Config(session)
//
//        // Do feature-specific operations here, such as enabling depth or turning on
//        // support for Augmented Faces.
//
//        // Configure the session.
//        session.configure(config)
//    }
}
