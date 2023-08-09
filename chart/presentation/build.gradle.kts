plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.parcelize")
}

android {
    namespace = "com.kursx.chart.presentation"
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
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.viewmodel.lifecycle)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")

    implementation(project(":chart-domain"))
    implementation(project(":common-domain"))
    implementation(project(":analytics-api"))

    implementation(project(":common-presentation"))
}
