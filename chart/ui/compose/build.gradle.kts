plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.kursx.demo.chart.ui.compose"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
}

dependencies {
    implementation(project(":chart-presentation"))
    implementation(project(":common-presentation"))
    implementation(project(":common-ui"))
    implementation(project(":analytics-api"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.activity.compose)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.viewmodel.compose)
    implementation(libs.viewmodel.lifecycle)
    implementation(libs.compose.lifecycle)


    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation("com.patrykandpatrick.vico:core:1.8.0")
    implementation("com.patrykandpatrick.vico:compose:1.8.0")
}
