import AppDependencies.implementation
import AppDependencies.testImplementation
import AppDependencies.androidTestImplementation
import AppDependencies.draggerDependancy
import AppDependencies.hiltDependency


plugins {
    id ("com.android.application")
    id("com.google.gms.google-services")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android-extensions")


}
android {

    compileSdk = 31
    defaultConfig {
        applicationId = "com.suraksha.app"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Android
    implementation(AppDependencies.uiLibraries)
    implementation(AppDependencies.navLibraries)
    implementation(AppDependencies.rxLibraries)
    implementation(AppDependencies.dataStoreLibraries)
    implementation(AppDependencies.retrofitCloudLibraries)
    implementation(project(mapOf("path" to ":cloud")))
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
    hiltDependency()
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}")
    // otp Viewer
    implementation("com.github.mukeshsolanki:android-otpview-pinview:2.0.0")
    // country code picker
    implementation("com.hbb20:ccp:2.4.5")
    //celebration view
    implementation( "nl.dionsegijn:konfetti-xml:2.0.2")
    implementation ("nl.dionsegijn:konfetti-compose:2.0.2")
 // profile upload
    implementation ("de.hdodenhof:circleimageview:3.0.2")
    implementation ("com.github.yalantis:ucrop:2.2.2")
    implementation ("com.karumi:dexter:5.0.0")


 // firebase
    implementation (platform("com.google.firebase:firebase-bom:30.0.2"))
    implementation ("com.google.firebase:firebase-analytics-ktx")
    implementation  ("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-core")
    // glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    implementation ("com.android.support:design:29.1.1")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    //image slider

    implementation ("com.github.smarteist:autoimageslider:1.4.0")






}
// Allow references to generated code
kapt { correctErrorTypes = true }