import AppDependencies.AndroidTestDependencies.androidJunit
import AppDependencies.AndroidTestDependencies.espresso
import AppDependencies.AndroidTestDependencies.navTest
import AppDependencies.DataStoreDependencies.dataStorePreferences
import AppDependencies.DataStoreDependencies.dataStoreTyped
import AppDependencies.NavigationDependencies.navDynamic
import AppDependencies.NavigationDependencies.navKtx
import AppDependencies.NavigationDependencies.navUiKtx
import AppDependencies.RetrofitCloudDependencies.logInter
import AppDependencies.TestDependencies.mokito
import AppDependencies.TestDependencies.testJunit
import AppDependencies.UiDependencies.appcompat
import AppDependencies.UiDependencies.constraintLayout
import AppDependencies.UiDependencies.coreKtx
import AppDependencies.UiDependencies.material
import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {

    object UiDependencies {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constrainLayout}"
    }

    object NavigationDependencies {
        const val navKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
        const val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
        const val navDynamic = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navVersion}"
    }

    object DataStoreDependencies {
        const val dataStoreTyped = "androidx.datastore:datastore-core:${Versions.dataStoreTypedVersion}"
        const val dataStorePreferences = "androidx.datastore:datastore-preferences:${Versions.dataStorePreferencesVersion}"
    }

    object RxJavaDependencies {
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAdroidVersion}"
        const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    }

    object RetrofitCloudDependencies {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
        const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
        const val logInter = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object TestDependencies {
        const val testJunit = "junit:junit:${Versions.junit}"
        const val mokito = "org.mockito.kotlin:mockito-kotlin:${Versions.mokito}"
    }

    object AndroidTestDependencies {
        const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val navTest = "androidx.navigation:navigation-testing:${Versions.navVersion}"
    }

    val uiLibraries = arrayListOf<String>().apply {
        add(material)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
    }

    val navLibraries = arrayListOf<String>().apply {
        add(navKtx)
        add(navUiKtx)
        add(navDynamic)
    }
    val dataStoreLibraries = arrayListOf<String>().apply {
        add(dataStoreTyped)
        add(dataStorePreferences)
    }

    val rxLibraries = arrayListOf<String>().apply {
        add(RxJavaDependencies.rxAndroid)
        add(RxJavaDependencies.rxJava)
    }

    val retrofitCloudLibraries = arrayListOf<String>().apply {
        add(RetrofitCloudDependencies.retrofit)
        add(RetrofitCloudDependencies.retrofitGson)
        add(RetrofitCloudDependencies.gson)
        add(logInter)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(testJunit)
        add(mokito)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(androidJunit)
        add(espresso)
        add(navTest)
    }

    fun DependencyHandler.compileOnly(dependency: String) {
        add("compileOnly", dependency)
    }

    fun DependencyHandler.kapt(list: List<String>) {
        list.forEach { dependency ->
            add("kapt", dependency)
        }
    }

    fun DependencyHandler.kapt(dependency: String) {
        add("kapt", dependency)
    }

    fun DependencyHandler.kaptTest(dependency: String) {
        add("kaptTest", dependency)
    }

    fun DependencyHandler.kaptAndroidTest(dependency: String) {
        add("kaptAndroidTest", dependency)
    }

    fun DependencyHandler.implementation(list: List<String>) {
        list.forEach { dependency ->
            add("implementation", dependency)
        }
    }

    private fun DependencyHandler.implementation(dependency: String) {
        add("implementation", dependency)
    }

    private fun DependencyHandler.annotationProcessor(dependency: String) {
        add("annotationProcessor", dependency)
    }

    fun DependencyHandler.compile(dependency: String) {
        add("implementation", dependency)
    }

    fun DependencyHandler.androidTestImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("androidTestImplementation", dependency)
        }
    }

    fun DependencyHandler.testImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("testImplementation", dependency)
        }
    }

    fun DependencyHandler.testImplementation(dependency: String) {
        add("testImplementation", dependency)
    }

    fun DependencyHandler.androidTestImplementation(dependency: String) {
        add("androidTestImplementation", dependency)
    }

    fun DependencyHandler.draggerDependancy() {
        implementation("com.google.dagger:dagger:${Versions.daggerVersion}")
        kapt("com.google.dagger:dagger-compiler:${Versions.daggerVersion}")
        compileOnly("javax.annotation:jsr250-api:1.0")


        // Testing Dagger
        kaptTest("com.google.dagger:dagger-compiler:${Versions.daggerVersion}")
        kaptAndroidTest("com.google.dagger:dagger-compiler:${Versions.daggerVersion}")
    }

    fun DependencyHandler.hiltDependency(){
        //implementation("androidx.hilt:hilt-work:1.0.0")
        // When using Kotlin.
        kapt("androidx.hilt:hilt-compiler:1.0.0")
        // when using navigation library
        implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
        implementation("com.google.dagger:hilt-android:${Versions.hiltVersion}")
        kapt("com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}")
    }

    fun DependencyHandler.testDependency(){
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.3")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    }

    fun DependencyHandler.coroutineDependency(){
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutinesVersion}")
    }

}