import AppDependencies.coroutineDependency
import AppDependencies.hiltDependency
import AppDependencies.implementation
import AppDependencies.testDependency

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        dataBinding = true
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
        kotlinOptions {
            jvmTarget = "1.8"
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    }
}

dependencies {

    implementation(AppDependencies.uiLibraries)
    implementation(AppDependencies.retrofitCloudLibraries)
    implementation("androidx.databinding:databinding-runtime:4.2.2")
    hiltDependency()
    testDependency()
    coroutineDependency()
}

// Allow references to generated code
kapt { correctErrorTypes = true }