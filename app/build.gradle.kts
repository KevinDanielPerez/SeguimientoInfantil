plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.seguimientoinfantilapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.seguimientoinfantilapp"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    implementation("com.android.volley:volley:1.2.0") // Para manejar las solicitudes HTTP
    implementation("com.google.android.material:material:1.9.0") // Material Design
    implementation("androidx.recyclerview:recyclerview:1.3.1") // Lista de actividades



    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


}