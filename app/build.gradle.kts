plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.arnolibs"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.arnolibs"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}


buildscript {
    repositories {
        google()
    }
}

dependencies {
    implementation ("com.google.ar:core:1.41.0")
    // Obj - a simple Wavefront OBJ file loader
    // https://github.com/javagl/Obj
    implementation ("de.javagl:obj:0.4.0")
    implementation ("androidx.appcompat:appcompat:1.1.0")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.2.0")

    implementation ("com.google.android.material:material:1.1.0")

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.21")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    //navigation
    implementation ("androidx.navigation:navigation-runtime-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-compose:2.7.7")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
//koin

    //Dependency injection
    implementation ("io.insert-koin:koin-core:3.5.0")
    implementation ("io.insert-koin:koin-android:3.5.0")
    implementation ("io.insert-koin:koin-androidx-compose:3.4.1")

}


//
//// Extracts the shared libraries from AARs in the native configuration
//// so that NDK builds can access these libraries.
//task extractNativeLibraries() {
//    // Extract every time.
//    outputs.upToDateWhen { false }
//
//    doFirst {
//        configurations.natives.files.each { f ->
//            copy {
//                from zipTree(f)
//                into arcore_libpath
//                        include "jni/**/*"
//            }
//        }
//    }
//}
//
//tasks.whenTaskAdded {
//        task-> if (task.name.contains("external") && !task.name.contains("Clean")) {
//    task.dependsOn(extractNativeLibraries)
//}
//}
//
//
//
//// From the sample app.
//externalNativeBuild {
//    cmake {
//        cppFlags "-std=c++11", "-Wall"
//        arguments "-DANDROID_STL=c++_static",
//        "-DARCORE_LIBPATH=${arcore_libpath}/jni",
//        "-DARCORE_INCLUDE=${project.rootDir}/../../libraries/include"
//    }
//}