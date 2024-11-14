plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.maps.secret)
}

android {
    namespace = "com.pemeta.pemetacard"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pemeta.pemetacard"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Navigation
    // Jetpack Compose integration
    implementation(libs.androidx.navigation.compose)
    // Views/Fragments integration
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    // Feature module support for Fragments
    implementation(libs.androidx.navigation.dynamic.features.fragment)
    // Testing Navigation
    androidTestImplementation(libs.androidx.navigation.testing)

    // navigation dan material design 3
    implementation (libs.material3)
    implementation (libs.androidx.material3)
    // implementation (libs.material3.window.size.class)

    // Permission
    implementation(libs.accompanist.permissions)
    implementation(libs.coil.compose)


    //animation
    implementation (libs.accompanist.navigation.animation)

    implementation (libs.androidx.navigation.compose)
    implementation (libs.androidx.core.splashscreen)
    implementation (libs.accompanist.flowlayout)
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    // Maps
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)
    // Android Maps Compose composables for the Maps SDK for Android
    implementation(libs.maps.compose.v610)
    //Secret Key-Maps
    implementation(libs.secrets.gradle.plugin)

    // Optionally, you can include the Compose utils library for Clustering, etc.
    implementation(libs.maps.compose.utils)
    // Optionally, you can include the widgets library for ScaleBar, etc.
    implementation(libs.maps.compose.widgets)
    // KTX for the Maps SDK for Android
    implementation(libs.maps.ktx)
    // (Optional) KTX for the Maps SDK for Android Utility Library
    implementation(libs.maps.utils.ktx)
    // Utilities for Maps SDK for Android (requires Google Play Services)
    implementation(libs.android.maps.utils)

    // CameraX core library using the camera2 implementation
    // The following line is optional, as the core library is included indirectly by camera-camera2
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    // If you want to additionally use the CameraX Lifecycle library
    implementation (libs.androidx.camera.lifecycle)
    // If you want to additionally use the CameraX VideoCapture library
    implementation (libs.androidx.camera.video)
    // If you want to additionally use the CameraX View class
    implementation (libs.androidx.camera.view)
    // If you want to additionally add CameraX ML Kit Vision Integration
    implementation (libs.androidx.camera.mlkit.vision)
    // If you want to additionally use the CameraX Extensions library
    implementation (libs.androidx.camera.extensions)
}