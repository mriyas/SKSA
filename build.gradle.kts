// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply("config.gradle")

buildscript {

    repositories {

        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
    dependencies{
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
       classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}")
        classpath ("com.google.gms:google-services:${Versions.firebaseVersion}")
        classpath("com.jakewharton:butterknife-gradle-plugin:10.2.1")
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}")


    }


}

@Suppress("JcenterRepositoryObsolete") allprojects{
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven("https://jitpack.io")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
