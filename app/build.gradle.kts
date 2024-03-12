plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.test_first"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.test_first"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // UI
    implementation ("androidx.fragment:fragment-ktx:1.6.2")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.activity:activity-ktx:1.8.2")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.4")
    implementation ("com.hannesdorfmann:adapterdelegates4-kotlin-dsl:4.3.1")
    implementation ("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:4.3.1")
    implementation ("androidx.core:core-ktx:1.12.0")

    // Dagger
    implementation ("com.google.dagger:hilt-android:2.51")
    kapt ("com.google.dagger:hilt-android-compiler:2.51")

    // Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    // Network
    //noinspection BomWithoutPlatform
    implementation ("com.squareup.okhttp3:okhttp-bom:4.9.3")
    implementation ("com.squareup.okhttp3:okhttp")
    implementation ("com.squareup.okhttp3:logging-interceptor")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Json
    implementation ("com.squareup.moshi:moshi-kotlin:1.11.0")
    implementation ("com.squareup.moshi:moshi-adapters:1.11.0")
    implementation ("com.google.android.material:material:1.11.0")
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:1.11.0")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("jp.wasabeef:glide-transformations:4.3.0")
    kapt ("com.github.bumptech.glide:compiler:4.12.0")

    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Utils
    implementation ("com.jakewharton.timber:timber:5.0.1")
}