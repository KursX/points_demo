plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.kursx.demo.common.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        vectorDrawables {
            useSupportLibrary = true
        }
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
    implementation(libs.appcompat)

    implementation(libs.navigationFragment)
}
