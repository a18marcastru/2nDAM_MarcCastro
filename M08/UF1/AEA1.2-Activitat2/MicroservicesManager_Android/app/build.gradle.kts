plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.microservicesmanager"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.microservicesmanager"
        minSdk = 35
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

    implementation(libs.androidx.core.ktx.v1131)
    implementation(libs.androidx.lifecycle.runtime.ktx.v286)
    implementation(libs.androidx.activity.compose.v192)
    implementation(platform(libs.androidx.compose.bom.v20230800))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.compose.ui.ui.tooling.preview)
    implementation(libs.androidx.navigation.compose.v274)
    implementation(libs.coil.compose)
    implementation(libs.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.coil)
    implementation(libs.androidx.recyclerview)
    implementation(libs.gson.v285)
    implementation(libs.json)
    implementation(libs.socket.io.client)
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation ("androidx.room:room-ktx:2.5.2")
    implementation(libs.google.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.espresso.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}