plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.kursx.demo.common.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.viewmodel.lifecycle)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
}
